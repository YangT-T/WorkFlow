package com.workflow.controller.modelController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TaskController {
    @GetMapping("/taskList")
    public String getTaskList(){
        return "task-list";
    }
}
