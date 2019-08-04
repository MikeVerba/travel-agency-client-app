package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.services.AvatarService;

import com.example.travelagencyclientapp.app.models.services.UserService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public String uploadAvatar(@RequestParam("avatar") MultipartFile multipartFile){

        //todo added for log purpose, remove that in the future
        System.out.println("User Id: ");
        System.out.println(userService.getLoggedUser().getId());
        try {
            System.out.println(Arrays.toString(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatarService.uploadAvatar(multipartFile, userService.getLoggedUser().getId());

        return "redirect:/index";
    }

    @GetMapping("/avatar/{id}/image")
    public void renderAvatarFromDatabase(@PathVariable String id,
            HttpServletResponse response) throws IOException {

        UserEntity userEntity = userService.findById(Long.valueOf(id));

        if(userEntity.getImage() != null) {

            byte[] byteArray = new byte[userEntity.getImage().length];

            int i = 0;

            for (Byte wrappedByte : userEntity.getImage()) {
                byteArray[i++] = wrappedByte;
            }


            response.setContentType("image/jpg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }

    }
}





















