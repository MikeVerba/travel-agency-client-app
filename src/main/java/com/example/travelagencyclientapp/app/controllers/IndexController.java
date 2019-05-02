package com.example.travelagencyclientapp.app.controllers;


import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {


    private final UserSession userSession;


    private final UserService userService;

    @Autowired
    public IndexController(UserSession userSession, UserService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if(!userSession.isLogin()){
            return "redirect:/login";
        }

        model.addAttribute("accountStatus", userSession.getUserEntity().getAccountStatus());
        return "index";
    }

    @GetMapping("/{page}")
    public String index(Model model,
                        @PathVariable("page") int page){
        if(!userSession.isLogin()){
            return "redirect:/login";
        }


        return "index";
    }

    public UserSession getUserSession(){
        return userSession;
    }
}
