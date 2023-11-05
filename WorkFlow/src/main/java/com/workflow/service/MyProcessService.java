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

    public void startInstance(String id){
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(id);
        System.out.println(processInstance.getId());
    }

    public void startProcessWithMap(String processId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance test1 = runtimeService.startProcessInstanceByKey(processId);
        System.out.printf("process %s has been started.%n", test1.getId());
    }
//    public void startProcessWithMap()


//    public void

}
