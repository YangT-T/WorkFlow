package com.workflow.controller;

import com.workflow.entities.TaskEntity;
import com.workflow.entities.TaskHistoryEntity;
import com.workflow.service.MyHistoryService;
import com.workflow.service.MyTaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
@Autowired
    private MyTaskService myTaskService;
@Autowired
    private MyHistoryService myHistoryService;
    @GetMapping("/task/getAll")
    public List<TaskEntity> getAll(HttpServletRequest request){
        String assigneeKey = (String)request.getSession().getAttribute("id");
        return myTaskService.getAll(assigneeKey);
    }

    @GetMapping("/task/getAllByProcess")
    public List<TaskEntity> getAllByProcess(HttpServletRequest request,
                                   @RequestParam(value = "processKey", defaultValue = "test1") String processKey,
                                   @RequestParam(value = "assigneeKey", defaultValue = "Jack") String assigneeKey){
        assigneeKey = (String)request.getSession().getAttribute("id");
        System.out.println(processKey);
        System.out.println(assigneeKey);
        return myTaskService.getAll(assigneeKey);
    }

    @GetMapping("/task/complete")
    public void complete(@RequestParam(value="taskId")String taskId){
        myTaskService.completeTask(taskId);
    }

    @GetMapping("/task/getHistory")
    public List<TaskHistoryEntity> getHistory(HttpServletRequest request){
        String id=(String)request.getSession().getAttribute("id");
        return myHistoryService.getHistory(id);
    }

}
