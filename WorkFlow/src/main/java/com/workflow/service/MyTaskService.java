package com.workflow.service;

import com.workflow.entities.MyTaskEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MyTaskService {
    ProcessEngine processEngine;
    public List<MyTaskEntity> getAll(String assigneeKey){
        processEngine= ProcessEngines.getDefaultProcessEngine();
        org.activiti.engine.TaskService taskService = processEngine.getTaskService();
        System.out.println(assigneeKey);
        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateUser(assigneeKey)
                .list();
        List<Task> taskList2=taskService.createTaskQuery().taskAssignee(assigneeKey).list();
        for (Task item : taskList2) {
            boolean flag = true;
            for (Task value : taskList) {
                if (Objects.equals(value.getId(), item.getId())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                taskList.add(item);
            }
        }
        ArrayList<MyTaskEntity> entityList=new ArrayList<>();
        taskList.forEach(e->{
            entityList.add(new MyTaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getDescription(),e.getCreateTime(),e.getDueDate()));
        });
//        List<Group> list_g = processEngine.getIdentityService().createGroupQuery().groupMember(assigneeKey).list();
//        System.out.println("gsize:"+list_g.size());
//        list_g.forEach(group -> {
//            System.out.println(group.getId());
//            List<Task> list = processEngine.getTaskService().createTaskQuery().taskCandidateUser("bob").list();
//            System.out.println(list.size());
//            list.forEach(e->{
//                entityList.add(new MyTaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getDescription(),e.getCreateTime(),e.getDueDate()));
//            });
//        });
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
        List<Group> list_g = processEngine.getIdentityService().createGroupQuery().groupMember(assigneeKey).list();
        list_g.forEach(group -> {
            List<Task> list = processEngine.getTaskService().createTaskQuery().taskCandidateGroup(group.getId()).list();
            list.forEach(e->{
                entityList.add(new MyTaskEntity(e.getId(),e.getName(),e.getOwner(),e.getAssignee(),e.getDescription(),e.getCreateTime(),e.getDueDate()));

            });
        });
        System.out.println("getAllByProcess");
        return entityList;
    }

    public void completeTask(String taskId,String assigneeId){
        processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();
        taskService.claim(taskId,assigneeId);
        taskService.complete(taskId);
    }

    public void deleteTask(String taskId){
        processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();
        taskService.deleteTask(taskId);
    }

    public void completeTaskPlus(String taskId,String assigneeId, Map<String,Object> map){
        processEngine=ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();
        taskService.claim(taskId,assigneeId);
        taskService.complete(taskId,map);
    }
}
