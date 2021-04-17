package com.urealaden.codefellowship.CodeFellowship.ApplicationControllers;

import com.urealaden.codefellowship.CodeFellowship.Application.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @GetMapping("/")
    public String displayHomePage( Model m){
        return "index";
    }

    @GetMapping("/coders")
    public String showUsers(Principal p, Model m, String username, HttpServletRequest request){
//        System.out.println("p.getName() = " + p.getName());

        m.addAttribute("user",request.getUserPrincipal());
        return "coders";
    }


}
