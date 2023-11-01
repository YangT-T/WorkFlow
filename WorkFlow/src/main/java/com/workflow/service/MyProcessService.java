package com.workflow.service;

import com.workflow.entities.ProcessEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyProcessService {
    RuntimeService runtimeService;

//    public List<ProcessEntity> getDeployment(){
//        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
////        repositoryService.getn
//    }

    public void startProcessById(String processId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance test1 = runtimeService.startProcessInstanceByKey(processId);
        System.out.printf("process %s has been started.%n", test1.getId());
    }
//    public void startProcessWithMap()


//    public void

}
