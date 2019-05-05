package com.example.travelagencyclientapp.app.models.services;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {


    boolean uploadAvatar(MultipartFile avatar, Long userId);

    boolean deleteAvatar(Long userId);
}
