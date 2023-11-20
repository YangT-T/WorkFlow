package com.workflow.controller;

import com.workflow.entities.MyTaskEntity;
import com.workflow.entities.MyTaskHistoryEntity;
import com.workflow.service.MyHistoryService;
import com.workflow.service.MyProcessService;
import com.workflow.service.MyTaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void complete(HttpServletRequest request,@RequestParam(value="taskId")String taskId){
        System.out.println(taskId);
        String assigneeId= (String) request.getSession().getAttribute("id");
        myTaskService.completeTask(taskId,assigneeId);
    }
    @GetMapping("/deleteTask")
    public void delete(HttpServletRequest request,@RequestParam(value="taskId")String taskId){
        System.out.println(taskId);
        String assigneeId= (String) request.getSession().getAttribute("id");
        myTaskService.deleteTask(taskId);
    }

    @GetMapping("/getHistory")
    public List<MyTaskHistoryEntity> getHistory(HttpServletRequest request){
        String id=(String)request.getSession().getAttribute("id");
        return myHistoryService.getHistory(id);
    }

    @PostMapping("/completeWithMap")
    public void createInstance(HttpServletRequest request,@RequestParam(name = "id")String id,@RequestBody Map<String, Object> maps){
        System.out.println(maps);
        System.out.println(id);
        maps=(Map<String, Object>)maps.get("params");
        List<String> keys= (List<String>) maps.get("keys");
        List<String> values= (List<String>) maps.get("values");
        Map<String,Object> properties=new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            properties.put(keys.get(i), values.get(i));
        }
        HttpSession session = request.getSession();
        String assigneeId;
        assigneeId= (String) session.getAttribute("id");
        MyTaskService myTaskService = new MyTaskService();
        myTaskService.completeTaskPlus(id,assigneeId,properties);
    }

}
