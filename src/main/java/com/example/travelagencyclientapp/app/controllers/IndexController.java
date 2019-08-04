package com.example.travelagencyclientapp.app.controllers;


import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {


    private final UserSession userSession;
    private final UserService userService;

    @Autowired
    public IndexController(UserSession userSession, UserService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("index")
    public String index(Model model) {


        if(userSession.isLogin()) {

            model.addAttribute("userEntity", userService.getLoggedUser());
        }
        return "user-details";

    }


}
