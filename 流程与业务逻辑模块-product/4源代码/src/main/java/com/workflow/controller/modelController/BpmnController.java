package com.workflow.controller.modelController;

import com.workflow.service.MyBpmnParseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/bpmnPage")
public class BpmnController {
    @GetMapping("/initial")
    public String getBpmnInitial(Model model, @RequestParam("id") String deploymentId){
        model.addAttribute("deploymentId",deploymentId);
        System.out.println(deploymentId);
        return "fill-properties";
    }
//    @GetMapping("/initial/fill")
//    public String
    @GetMapping("/taskFlow")
    public String getBpmnFlow(Model model, @RequestParam("id") String taskId){
        model.addAttribute("taskId",taskId);
        return "fill-flow-properties";
    }
}
