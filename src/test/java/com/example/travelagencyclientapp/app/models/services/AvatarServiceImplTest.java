package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class AvatarServiceImplTest {

    @Mock
    UserRepository userRepository;


    AvatarService avatarService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        avatarService = new AvatarServiceImpl(userRepository);

    }


    @AfterEach
    void cleanUp(){

        reset(userRepository);
    }

    @Test
    @DisplayName("Testing persisting image to database as Byte array")
    void uploadAvatarTest() throws Exception {
        //given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile","testing.txt","text/plain","ABCDEFGHIJKLMN".getBytes());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        Optional<UserEntity> userEntityOptional = Optional.of(userEntity);


        given(userRepository.findById(anyLong())).willReturn(userEntityOptional);

        ArgumentCaptor<UserEntity> argumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        //when

        avatarService.uploadAvatar(multipartFile,id);

        //then

        verify(userRepository,times(1)).save(argumentCaptor.capture());
        UserEntity savedUser = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length,savedUser.getImage().length);
        assertTrue(avatarService.uploadAvatar(multipartFile,id));

    }
}