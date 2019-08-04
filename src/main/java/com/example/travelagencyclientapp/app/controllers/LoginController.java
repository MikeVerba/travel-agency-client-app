package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserServiceImpl;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class LoginController {

    private final UserService userService;
    private final UserSession userSession;

    @Autowired
    public LoginController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String getUser(@ModelAttribute("loginForm") @Valid LoginForm loginForm,
                           BindingResult bindingResult) {


        if(bindingResult.hasErrors()){
            return "login";
        } //todo added to check for errors


        //model.addAttribute("loginResponse", loginResponse);
        return "redirect:/login-success";
    }

    @GetMapping("/logout")
    public String logout() {

            userSession.logout();

        return "logout";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "login-success";
    }
}
