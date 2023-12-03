package com.workflow.controller.modelController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(HttpServletRequest request,@RequestParam(value = "userId")String userId,@RequestParam(value ="password")String password){
        HttpSession session = request.getSession();
        session.setAttribute("id",userId);
        System.out.println("login: session has been created, id is "+userId);
        // check
        return "redirect:/index";
    }
}
