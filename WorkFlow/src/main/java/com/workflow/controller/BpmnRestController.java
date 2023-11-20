package com.workflow.controller;

import com.workflow.service.MyBpmnParseService;
import com.workflow.service.MyProcessService;
import com.workflow.service.MyTaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bpmn")
public class BpmnRestController {
    @GetMapping("/initial/get")
    public List<String> getInitialProperties(@RequestParam("deploymentId") String deploymentId){
        MyBpmnParseService myBpmnParseService = new MyBpmnParseService();
        List<String> properties = myBpmnParseService.getInitialProperties(deploymentId);
        return properties;
    }

    @GetMapping("/Flow/get")
    public List<String> getFlowProperties(@RequestParam("taskId") String taskId){
        MyBpmnParseService myBpmnParseService = new MyBpmnParseService();
        List<String> properties = myBpmnParseService.getTaskFlowProperties(taskId);
        return properties;
    }

    @GetMapping("/initial/submit")
    public void submitInitialProperties(@RequestParam("deploymentId") String deploymentId,@RequestBody Map<String,Object> map){
        MyProcessService myProcessService = new MyProcessService();
        myProcessService.startInstancePlus(deploymentId,map);
    }

    @GetMapping("/flow/submit")
    public void submitFlowProperties(HttpServletRequest request, @RequestParam("taskId") String taskId, @RequestBody Map<String,Object> map){
        HttpSession session = request.getSession();
        String assigneeId;
        assigneeId= (String) session.getAttribute("id");
        MyTaskService myTaskService = new MyTaskService();
        myTaskService.completeTaskPlus(taskId,assigneeId,map);
    }

}
