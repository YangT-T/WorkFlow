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
import java.util.*;

public class BpmnParser {
    public static List<String> parseBpmnAssignee(String deploymentId) {
        List<String> result = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        if(list.isEmpty()){
            return result;
        }
        ProcessDefinition processDefinition = list.get(0);
        String fileName=processDefinition.getResourceName();
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
                    if (attr.getNodeName().equals("activiti:assignee")&&attr.getNodeValue().contains("$")) {
                        String fullString=attr.getNodeValue();
                        result.add(fullString.substring(2,fullString.length()-1));
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<String> parseBpmnAllTask(String deploymentId) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        ProcessDefinition processDefinition = list.get(0);
        String fileName=processDefinition.getResourceName();
        List<String> result = new ArrayList<>();
        try {
            InputStream processBpmn = processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), fileName);
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document parse = builder.parse(processBpmn);
            NodeList userTask = parse.getElementsByTagName("userTask");
            NodeList sequenceFlow = parse.getElementsByTagName("sequenceFlow");
            System.out.println(userTask.getLength());

            for (int i = 0; i < userTask.getLength(); i++) {
                Node item = userTask.item(i);
                NamedNodeMap attributes = item.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    Node attr = attributes.item(j);
                    System.out.println(attr.getNodeValue());
                    if (attr.getNodeValue().contains("$")) {
                        String fullString=attr.getNodeValue();
                        result.add(fullString.substring(2,fullString.length()-1));
                    }
                }
            }
            for (int i = 0; i < sequenceFlow.getLength(); i++) {
                Node item = sequenceFlow.item(i);
                NamedNodeMap attributes = item.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    Node attr = attributes.item(j);
                    System.out.println(attr.getNodeValue());
                    if (attr.getNodeValue().contains("$")) {
                        String fullString=attr.getNodeValue();
                        result.add(fullString.substring(2,fullString.length()-1));
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<String> parseBpmnTaskFlow(String deploymentId,String taskDefKey){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        ProcessDefinition processDefinition = list.get(0);
        String fileName=processDefinition.getResourceName();
        List<String> result = new ArrayList<>();
        HashSet<String> visited=new HashSet<>();
        try {
            InputStream processBpmn = processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), fileName);
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document parse = builder.parse(processBpmn);
            NodeList userTask = parse.getElementsByTagName("userTask");
            NodeList sequenceFlow = parse.getElementsByTagName("sequenceFlow");
            NodeList exclusiveGateway = parse.getElementsByTagName("exclusiveGateway");
            // 初始化gateway hashset
            HashSet<String> gateways=new HashSet<String>();
            for (int i = 0; i < exclusiveGateway.getLength(); i++) {
                gateways.add(exclusiveGateway.item(i).getAttributes().getNamedItem("id").getNodeValue());
            }

            Queue<String> taskQueue=new LinkedList<>();
            taskQueue.add(taskDefKey);
            while(!taskQueue.isEmpty()){
                String nodeId=taskQueue.remove();
                if(visited.contains(nodeId)){
                    continue;
                }
                visited.add(nodeId);
                System.out.println("taskKey: "+nodeId);
                for (int i = 0; i < sequenceFlow.getLength(); i++) {
                    Node item = sequenceFlow.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    boolean flag=false;
                    for (int j = 0; j < attributes.getLength(); j++) {
                        boolean isRelated=false;
                        Node attr = attributes.item(j);
                        // 判断任务是否相邻
                        if(attr.getNodeName().equals("sourceRef") && Objects.equals(attr.getNodeValue(), nodeId)){
                            // 将该任务所有待填属性添加到列表
                            flag=true;
                            System.out.println("find "+attr.getNodeValue());
                        }
                        // 判断是否要将网关加入队列
                        if(flag&&attr.getNodeName().equals("targetRef") && gateways.contains(attr.getNodeValue())){
                            System.out.println("add gateway"+attr.getNodeValue());
                            taskQueue.add(attr.getNodeValue());
                        }
                    }
                    // 添加属性
                    if(flag){
                        for (int j = 0; j < attributes.getLength(); j++) {
                            Node attr = attributes.item(j);
                            // 判断任务是否相邻
                            if (attr.getNodeName().equals("name")  && attr.getNodeValue().contains("$")){
                                String line=attr.getNodeValue();
                                List<String> strings = StringParser.parseStringAttr(line);
                                strings.forEach(e->{
                                    if(!result.contains(e)){
                                        result.add(e);
                                    }
                                });
                            }
                        }
                    }
                }

            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
