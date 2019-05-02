package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.UserForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddUserController {

    private final UserService userService;

    @Autowired
    public AddUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add-user")
    public String loginForm(Model model){
        model.addAttribute("user",new UserForm());
        return "add-user";
    }

    @PostMapping("/add-user")
    @ResponseBody
    public String getUser(@ModelAttribute UserForm userForm){
        System.out.println(userForm);
        if (!userService.addUser(userForm)) {
            return "username is busy";
        }
        return "Thank you!!";
    }
}
