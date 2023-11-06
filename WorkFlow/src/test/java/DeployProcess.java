import com.workflow.utils.BpmnParser;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Resources;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeployProcess {
    private String deploymentName="deployment_2";
    private String bpmnPath="bpmn/test.bpmn20.xml";
    private String jpgPath="bpmn-png/test1";

    private String deploymentId="20001";
    @Test
    public void deploy(){
        // 在后端部署流程
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name(deploymentName)
                .addClasspathResource(bpmnPath)
                .addClasspathResource(jpgPath)
                .deploy();
        deploymentId=deploy.getId();
        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
        System.out.println(deploy.getKey());
    }

    @Test
    public void fillPropertyAndStart(){
        //backend
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        ProcessDefinition processDefinition = list.get(0);
        String processId=processDefinition.getId();
        String resourceName = processDefinition.getResourceName();
        System.out.println(resourceName);
        List<String> toFill = BpmnParser.parseBpmnAssignee(deploymentId);
        //返回前端，前端填写所有属性(以toFill里的值为键),返回一个map
        Map<String,Object> map=new HashMap<>();
        map.put("student", "alex");
        map.put("leader", "yh");
        map.put("supervisor", "Jack");
        //后端根据map启动流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance =runtimeService.startProcessInstanceByKey("mapTest",map);
        System.out.println("start process instance id = "+processInstance.getId());
    }
}
