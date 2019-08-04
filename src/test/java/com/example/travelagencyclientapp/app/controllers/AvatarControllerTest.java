package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.services.AvatarService;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    UserService userService;

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
        reset(userService);
        reset(avatarService);
    }

    @Test
    void uploadAvatar() {
    }

    @Test
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

        given(userService.getLoggedUser()).willReturn(userEntity);

        //when

        MockHttpServletResponse response = mockMvc.perform(get("/avatar"))
                .andExpect(status().isOk())
                .andReturn().getResponse();


        //then

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length,responseBytes.length);


    }
}