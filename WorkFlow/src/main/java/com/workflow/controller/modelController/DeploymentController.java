package com.workflow.controller.modelController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeploymentController {
    @GetMapping("/deploymentPage")
    public String deploymentPage(){
        return "deployment";
    }

}
