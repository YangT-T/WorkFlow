package com.workflow.controller;

import com.workflow.entities.MyTaskEntity;
import com.workflow.entities.MyTaskHistoryEntity;
import com.workflow.service.MyHistoryService;
import com.workflow.service.MyTaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskRestController {
@Autowired
    private MyTaskService myTaskService;
@Autowired
    private MyHistoryService myHistoryService;
    @GetMapping("/getAll")
    public List<MyTaskEntity> getAll(HttpServletRequest request){
        String assigneeKey = (String)request.getSession().getAttribute("id");
        return myTaskService.getAll(assigneeKey);
    }

    @GetMapping("/getAllByProcess")
    public List<MyTaskEntity> getAllByProcess(HttpServletRequest request,
                                              @RequestParam(value = "processKey", defaultValue = "test1") String processKey,
                                              @RequestParam(value = "assigneeKey", defaultValue = "Jack") String assigneeKey){
        assigneeKey = (String)request.getSession().getAttribute("id");
        System.out.println(processKey);
        System.out.println(assigneeKey);
        return myTaskService.getAll(assigneeKey);
    }

    @GetMapping("/completeTask")
    public void complete(@RequestParam(value="taskId")String taskId){
        System.out.println(taskId);
        myTaskService.completeTask(taskId);
    }

    @GetMapping("/getHistory")
    public List<MyTaskHistoryEntity> getHistory(HttpServletRequest request){
        String id=(String)request.getSession().getAttribute("id");
        return myHistoryService.getHistory(id);
    }

}
