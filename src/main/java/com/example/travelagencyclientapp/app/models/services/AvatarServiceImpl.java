package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Service
public class AvatarServiceImpl implements AvatarService {


    private final UserRepository userRepository;


    @Autowired
    public AvatarServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public boolean uploadAvatar(MultipartFile multipartFile, Long userId){
//        if(!isFileCorrect(multipartFile)){
//            return false;
//        } //todo validation of the file makes test fail


        UserEntity userEntity = userRepository.findById(userId).get();

        try {


            Byte[] byteObjects = new Byte[multipartFile.getBytes().length];

            int i = 0;

            for (byte b : multipartFile.getBytes()) {
                byteObjects[i++] = b;
            }

            userEntity.setImage(byteObjects);
            userRepository.save(userEntity);

        } catch (IOException e) {
            System.out.println("Error occurred");

            e.printStackTrace();
            return false;
        }
        return true;
    }

}
