import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStartProcess {
    @Test
    public void startProcess(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance test1 = runtimeService.startProcessInstanceByKey("test1");
        System.out.println(test1.getId());
    }

    @Test
    public void startProcessById(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        String id="2501";
        List<ProcessDefinition> list = defaultProcessEngine.getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(id).list();
        ProcessDefinition processDefinition = list.get(0);
        String processId=processDefinition.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("employeeId","a");
//        ProcessInstance test1 = runtimeService.startProcessInstanceById(processId);
        ProcessInstance test1 = runtimeService.startProcessInstanceById(processId,map);
        System.out.println(test1.getId());
    }

    @Test
    public void testStartProcessByMap(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        Map<String, Object> map=new HashMap<>();
        map.put("student","alex");
        runtimeService.startProcessInstanceByKey("mapTest",map);
    }

    @Test
    public void getProcessKey(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        System.out.println(repositoryService.getDeploymentResourceNames("mapTest"));
//        Model mapTest = repositoryService.getModel("mapTest");
        DynamicBpmnService dynamicBpmnService = defaultProcessEngine.getDynamicBpmnService();
//        dynamicBpmnService.get
//        defaultProcessEngine.get
//        defaultProcessEngine.getIdentityService();
//        System.out.println(mapTest.getDataStores());
    }
}
