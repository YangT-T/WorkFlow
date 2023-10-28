package com.workflow.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
//    @GetMapping ("/index")
//    public String getIndex(){
//        return "index";
//    }
    @GetMapping("/taskList")
    public String getTaskList(){
        return "task-list";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(HttpServletRequest request,@RequestParam(value = "userId")String userId,@RequestParam(value ="password")String password){
//        System.out.println(username);
        HttpSession session = request.getSession();
        session.setAttribute("id",userId);
        // check
        return "redirect:/index";
    }


}
