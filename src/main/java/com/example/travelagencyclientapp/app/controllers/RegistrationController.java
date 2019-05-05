package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {

        this.userService = userService;

    }


    @GetMapping("/registration-form")
    public String registrationForm(Model model){
        model.addAttribute("registrationForm",new RegistrationForm());
        return "registration-form";
    }

    @PostMapping("/registration-form")
    public String registrationForm(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm,
                                   BindingResult bindingResult,
                                   Model model){

        if(bindingResult.hasErrors()){

            return "registration-form";
        }

        if (userService.addUser(registrationForm)) {
            return "registration-success";
        }

        model.addAttribute("usernameError","your username is busy!");
        return "registration-form";
    }
}
