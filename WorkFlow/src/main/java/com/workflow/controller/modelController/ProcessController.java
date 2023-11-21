package com.workflow.controller.modelController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessController {
    @GetMapping("/processPage")
    public String processPage(){
        return "process-list";
    }
}
