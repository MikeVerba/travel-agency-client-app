package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.UserForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    final UserService userService;

    final
    UserSession userSession;

    @Autowired
    public LoginController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }



    @GetMapping({"/login","/"})
    public String loginForm(Model model){
        model.addAttribute("user",new UserForm());
        return "login";
    }

    @PostMapping("/login")
    public String getUser(@ModelAttribute UserForm user,
                          Model model){

        UserService.LoginResponse loginResponse = userService.login(user);
        if(loginResponse == UserService.LoginResponse.SUCCESS){
            return "redirect:/";
        }

        model.addAttribute("loginResponse", loginResponse);
        return "login";
    }

    @GetMapping("/logout")
    public  String logout(){
        userSession.logout();
        return "redirect:/login";
    }
}
