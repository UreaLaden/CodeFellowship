package com.urealaden.codefellowship.CodeFellowship.ApplicationControllers;


import com.urealaden.codefellowship.CodeFellowship.Application.ApplicationUser;
import com.urealaden.codefellowship.CodeFellowship.Application.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @PostMapping("/appUser")
    public RedirectView createUser(String username, String password){
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
        return new RedirectView("/coders");
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "index";
    }

}
