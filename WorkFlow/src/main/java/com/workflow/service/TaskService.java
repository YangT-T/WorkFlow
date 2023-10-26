package com.workflow.service;

import com.workflow.entities.TaskEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class TaskService {
    ProcessEngine processEngine;
    public List<TaskEntity> getAll(String processKey, String assigneeKey){
        processEngine= ProcessEngines.getDefaultProcessEngine();
        org.activiti.engine.TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processKey)
                .taskAssignee(assigneeKey)
                .list();
        System.out.println(taskList.size());
        ArrayList<TaskEntity> entityList=new ArrayList<>();
        taskList.forEach(e->{
            entityList.add(new TaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getCreateTime()));
        });
        System.out.println("getAll");
        return entityList;
    }

}
