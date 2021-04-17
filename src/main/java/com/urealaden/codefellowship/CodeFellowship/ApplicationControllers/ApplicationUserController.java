package com.urealaden.codefellowship.CodeFellowship.ApplicationControllers;


import com.urealaden.codefellowship.CodeFellowship.Application.ApplicationUser;
import com.urealaden.codefellowship.CodeFellowship.Application.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @PostMapping("/appUser")
    public RedirectView createUser(String username, String password, Model model,
                                   Principal principal){
        password = passwordEncoder.encode(password);
        ApplicationUser appUser = new ApplicationUser();
        System.out.println("password = " + password);
        System.out.println("username = " + username);
        appUser.setPassword(password);
        appUser.setUsername(username);
        appUser.setBio("");
        appUser.setFirstName("");
        appUser.setLastName("");
        appUser.setDateOfBirth("");
        applicationUserRepository.save(appUser);
        model.addAttribute("user",principal);

        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String showLoginPage(Principal p, Model m){
        System.out.println("p from /login GET= " + p);
        m.addAttribute("user",p);
        return "login";
    }

    @PostMapping("/login")
    public RedirectView renderLogin(Principal principal, Model model, String username){
        model.addAttribute("user",principal);
//        System.out.println("Principal from /login POST"+principal);
        return new RedirectView("/coders");
    }

    @GetMapping("/signUp")
    public String showSignUpPage(){
        return "sign-up";
    }
}
