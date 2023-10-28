package com.workflow.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HistoryController {
    @GetMapping("/historyPage")
    public String historyPage(HttpServletRequest request){
        return "history-list";
    }
    @GetMapping("/history")
    public String history(HttpServletRequest request){
        return "redirect:/historyPage";
    }


}
