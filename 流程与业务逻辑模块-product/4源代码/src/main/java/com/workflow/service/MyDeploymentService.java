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
//        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        List<MyDeploymentEntity> myList=new ArrayList<>();
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
//        list.forEach(e->{
//            System.out.println(e.getId());
//            myList.add(new MyDeploymentEntity(e.getId(),e.getName(),e.getDescription(),e.isSuspended()));
//        });
        list.forEach(e->{
            System.out.println(e.getId());
            myList.add(new MyDeploymentEntity(e.getId(),e.getName(),e.getCategory(),false));
        });
        return myList;
    }

    public void startInstance(String id){
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(id);
        System.out.println(processInstance.getId());
    }

    public String getJPGE(String deploymentId){
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        List<ProcessDefinition> list = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().
                orderByProcessDefinitionVersion().asc().
                deploymentId(deploymentId).list();
        ProcessDefinition processDefinition = list.get(0);
        String id=processDefinition.getId();
        ProcessDefinition processDefinition1 = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).list().get(0);
        return processDefinition1.getDiagramResourceName();
//        return repositoryService.getDeploymentResourceNames(id).get(0);
    }


}
