package com.workflow.service;

import com.workflow.utils.BpmnParser;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;

import java.util.List;

public class MyBpmnParseService {
    public List<String> getInitialProperties(String deploymentId){
        List<String> properties = BpmnParser.parseBpmnAssignee(deploymentId);
        return properties;
    }

    public List<String> getTaskFlowProperties(String taskId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskId(taskId).list();
        String processDefinitionId = list.get(0).getProcessDefinitionId();
        String taskDefKey=list.get(0).getTaskDefinitionKey();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        String deploymentId = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).list().get(0).getDeploymentId();
        System.out.println("123123123123:"+taskDefKey);
        return BpmnParser.parseBpmnTaskFlow(deploymentId, taskDefKey);
    }

}
