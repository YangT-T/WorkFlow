package com.workflow.service;

import com.workflow.entities.MyProcessEntity;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MyProcessService {
    RuntimeService runtimeService;

    public void startInstance(String id){
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
               List<ProcessDefinition> list = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(id).list();
        ProcessDefinition processDefinition = list.get(0);
        id=processDefinition.getId();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(id);
        System.out.println(processInstance.getId());
    }

    public void startProcessWithMap(String processId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance test1 = runtimeService.startProcessInstanceByKey(processId);
        System.out.printf("process %s has been started.%n", test1.getId());
    }

    public void startInstancePlus(String id, Map<String,Object> map){
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
        List<ProcessDefinition> list = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(id).list();
        ProcessDefinition processDefinition = list.get(0);
        id=processDefinition.getId();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(id,map);
        System.out.println(processInstance.getId());
    }

    public List<MyProcessEntity> getAllProcessInfo(){
        List<MyProcessEntity> result=new ArrayList<>();
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        HistoryService historyService = defaultProcessEngine.getHistoryService();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        list.forEach(process->{
            String processId=process.getId();

            List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery()
//                    .processDefinitionId(processId)
                    .processInstanceId(processId)
//                    .orderByHistoricTaskInstanceStartTime()
                    .list();


            String starter= tasks.get(0).getAssignee();

            String currentAssignee= tasks.get(tasks.size()-1).getAssignee();

            String state;
            if(process.getEndTime()==null){
                state="unfinished";
            }else{
                state="finished";
            }

            Date startTime=process.getStartTime();
            result.add(new MyProcessEntity(processId,starter,currentAssignee,state,startTime));
        });
        return result;
    }

//    public void

}
