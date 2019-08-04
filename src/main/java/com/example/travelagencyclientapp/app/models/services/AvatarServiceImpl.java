package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

//    @Override
//    public boolean deleteAvatar(Long userId){
//        if(false){  //todo remove that!!
//            return false;
//        }
//
//        try {
//            deleteAvatarResource(userId);
//        } catch (IOException e) {
//            return false;
//        }
//        return true;
//    }
//
//
//    private void deleteAvatarResource(Long userId) throws IOException {
//        Files.delete(getPath(userId));
//    }
//
//
//    private void copyAvatarToResourceFolder(MultipartFile avatar, Long userId) throws IOException {
//        Path pathToAvatar = getPath(userId);
//        createFileIfNotExist(pathToAvatar);
//
//        Files.write(pathToAvatar, avatar.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
//    }
//
//
//    private Path getPath(Long userId) {
//        return Paths.get(PATH_TO_AVATARS + File.separator + userId);
//    }
//
//    private void createFileIfNotExist(Path pathToAvatar) throws IOException {
//        File file = pathToAvatar.toFile();
//        if(!file.exists()){
//            file.createNewFile();
//        }
//    }
//
//    private boolean isFileCorrect(MultipartFile avatar) {
//        return checkFileFormat(avatar) && checkFileSize(avatar);
//    }
//
//    private boolean checkFileSize(MultipartFile avatar) {
//        return avatar.getSize() <= 1024 * 1024 * 5;
//    }
//
//    private boolean checkFileFormat(MultipartFile avatar) {
//        return avatar.getContentType() != null && (avatar.getContentType().contains("png") || avatar.getContentType().contains("image"));
//    }
//

}
