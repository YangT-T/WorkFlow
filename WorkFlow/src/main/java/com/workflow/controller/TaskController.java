package com.workflow.controller;

import com.workflow.entities.TaskEntity;
import com.workflow.service.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
@Autowired
    private TaskService taskService;
    @GetMapping("/task/getAll")
    public List<TaskEntity> getAll(@RequestParam(value = "processKey", defaultValue = "test1") String processKey,
        @RequestParam(value = "assigneeKey", defaultValue = "alex") String assigneeKey){
        System.out.println(processKey);
        System.out.println(assigneeKey);
        return taskService.getAll(processKey,assigneeKey);
    }
}
