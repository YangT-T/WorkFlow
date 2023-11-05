package com.workflow.controller;

import com.workflow.entities.MyDeploymentEntity;
import com.workflow.service.MyDeploymentService;
import com.workflow.service.MyProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deployment")
public class DeploymentRestController {
    @Autowired
    MyDeploymentService deploymentService;
    @GetMapping("/getAll")
    public List<MyDeploymentEntity> getAllDeployment(){
        return deploymentService.getAllDeploymentService();
    }
    @GetMapping("/createInstance")
    public void createInstance(@RequestParam(name = "id")String id){
        MyProcessService myProcessService = new MyProcessService();
        myProcessService.startInstance(id);
        System.out.println("start process instance, id = "+id);
    }
    @GetMapping("/createInstanceWithMap")
    public void createInstance(){

    }

}
