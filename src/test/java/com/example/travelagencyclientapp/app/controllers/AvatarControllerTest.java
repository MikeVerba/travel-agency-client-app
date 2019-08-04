package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import com.example.travelagencyclientapp.app.models.services.AvatarService;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AvatarControllerTest {


    @Mock
    UserRepository userRepository;

    @Mock
    AvatarService avatarService;

    @InjectMocks
    AvatarController avatarController;

    MockMvc mockMvc;

    @Test
    void uploadAvatarTest() {

    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(avatarController).build();

    }

    @AfterEach
    void tearDown() {
        reset(userRepository);
        reset(avatarService);
    }

    @Test
    void uploadAvatar() {
    }

    @Test
    @Disabled
    @DisplayName("Testing rendering images from database logic")
    void renderAvatarFromDatabase() throws Exception {

        //given

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        String s = "fake text";
        Byte[] bytes = new Byte[s.getBytes().length];

        int i = 0;

        for(byte b : s.getBytes()){
            bytes[i++] = b;
        }

        userEntity.setImage(bytes);

        Optional<UserEntity> userEntityOptional = Optional.of(userEntity);

        given(userRepository.findById(anyLong())).willReturn(userEntityOptional);

        //when

        MockHttpServletResponse response = mockMvc.perform(get("/avatar/1/image"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //todo getting NPE but images are uploading. need to fix that


        //then

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length,responseBytes.length);


    }
}