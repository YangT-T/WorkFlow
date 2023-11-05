package com.workflow.service;

import com.workflow.entities.MyTaskEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyTaskService {
    ProcessEngine processEngine;
    public List<MyTaskEntity> getAll(String assigneeKey){
        processEngine= ProcessEngines.getDefaultProcessEngine();
        org.activiti.engine.TaskService taskService = processEngine.getTaskService();
        System.out.println(assigneeKey);
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(assigneeKey)
                .list();
        System.out.println(taskList.size());
        ArrayList<MyTaskEntity> entityList=new ArrayList<>();
        taskList.forEach(e->{
            entityList.add(new MyTaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getDescription(),e.getCreateTime(),e.getDueDate()));
        });
        System.out.println("getAll");
        return entityList;
    }

    public List<MyTaskEntity> getAllByProcess(String processKey, String assigneeKey){
        processEngine= ProcessEngines.getDefaultProcessEngine();
        org.activiti.engine.TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processKey)
                .taskAssignee(assigneeKey)
                .list();
        System.out.println(taskList.size());
        ArrayList<MyTaskEntity> entityList=new ArrayList<>();
        taskList.forEach(e->{
            entityList.add(new MyTaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getDescription(),e.getCreateTime(),e.getDueDate()));
        });
        System.out.println("getAllByProcess");
        return entityList;
    }

    public void completeTask(String taskId){
        processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();
//        taskService.id
        taskService.complete(taskId);
    }


}
