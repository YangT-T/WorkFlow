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
    @Test
    public void Deploy(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name(deploymentName)
                .addClasspathResource(bpmnPath)
                .addClasspathResource(jpgPath)
                .deploy();
        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
        System.out.println(deploy.getKey());
    }

    @Test
    public void testStart(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//        runtimeService.startProcessInstanceByKey(repository)
//        repositoryService.setDeploymentKey();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition("17501");
        System.out.println(processDefinition.getKey());
//        runtimeService
    }

    @Test
    public void testStep1(){
        String name="aaa";
        String resourcePath="bpmn/mapTest.bpmn20.xml";
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name(name)
                .addClasspathResource(resourcePath)
                .deploy();
        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
        System.out.println(deploy.getKey());
    }

    @Test
    public void testParse(){
        String deploymentId="20001";
        String fileName="bpmn/"+"mapTest"+".bpmn20.xml";

        List<String> strings = BpmnParser.parseBpmnAssignee(deploymentId, fileName);

    }

    @Test
    public void testStartProcess(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService= engine.getRuntimeService();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("student", "alex");
        map.put("leader", "yh");
        map.put("supervisor", "Jack");

        ProcessInstance processInstance =runtimeService.startProcessInstanceByKey("mapTest",map);

        System.out.println(processInstance.getId());//流程图id
    }


    @Test
    public void testStep2(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        FormService formService = defaultProcessEngine.getFormService();
        StartFormData startFormData = formService.getStartFormData("mapTest:2:22504");
        List<FormProperty> formProperties = startFormData.getFormProperties();
        System.out.println(formProperties.size());

    }

}
