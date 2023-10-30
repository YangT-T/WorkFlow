package com.workflow.service;

import com.workflow.entities.MyDeploymentEntity;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDeploymentService {
    public List<MyDeploymentEntity> getAllDeploymentService(){
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        List<MyDeploymentEntity> myList=new ArrayList<>();
        list.forEach(e->{
            System.out.println(e.getId());
            myList.add(new MyDeploymentEntity(e.getId(),e.getName(),e.getDescription(),e.isSuspended()));
        });
        return myList;
    }

    public void startInstance(String id){
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(id);
        System.out.println(processInstance.getId());
    }



}
