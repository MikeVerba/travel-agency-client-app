package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.services.AvatarService;

import com.example.travelagencyclientapp.app.models.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class AvatarController {


    private final AvatarService avatarService;
    private final UserService userService;

    @Autowired
    public AvatarController(AvatarService avatarService, UserService userService) {
        this.avatarService = avatarService;
        this.userService = userService;
    }


    @PostMapping("/avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile multipartFile,
                               RedirectAttributes redirectAttributes){

        System.out.println("User Id: ");
        System.out.println(userService.getLoggedUser().getId()); //todo remove when I know how to upload avatar!
        try {
            System.out.println(Arrays.toString(multipartFile.getBytes())); //todo remove, for testing
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatarService.uploadAvatar(multipartFile, userService.getLoggedUser().getId());

        return "redirect:/index";
    }
}





















