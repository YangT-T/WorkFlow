package com.workflow.controller;

import com.workflow.entities.MyProcessEntity;
import com.workflow.service.MyProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessRestController {
    @GetMapping("/getAll")
    public List<MyProcessEntity> getProcessInfo(){
        MyProcessService myProcessService = new MyProcessService();
        return myProcessService.getAllProcessInfo();
    }
}
