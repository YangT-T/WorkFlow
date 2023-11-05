package com.workflow.utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BpmnParser {
    public static List<String> parseBpmnAssignee(String deploymentId,String fileName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        ProcessDefinition processDefinition = list.get(0);
        List<String> result = new ArrayList<>();
        try {
            InputStream processBpmn = processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), fileName);
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document parse = builder.parse(processBpmn);
            NodeList userTask = parse.getElementsByTagName("userTask");
            System.out.println(userTask.getLength());

            for (int i = 0; i < userTask.getLength(); i++) {
                Node item = userTask.item(i);
                NamedNodeMap attributes = item.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    Node attr = attributes.item(j);
                    if (attr.getNodeName().equals("activiti:assignee")) {
                        result.add(attr.getNodeValue());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
