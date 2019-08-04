package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    UserService userService;

    @Mock
    UserSession userSession;

    @InjectMocks
    IndexController indexController;

    @Mock
    Model model;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @AfterEach
    void tearDown() {
        reset(userService);
        reset(userSession);
    }

    @Test
    @DisplayName("Testing index get with Mock Mvc")
    void indexTestingWithMockMvc() throws Exception{


        given(userSession.isLogin()).willReturn(true);
        given(userService.getLoggedUser()).willReturn(new UserEntity());

        mockMvc.perform(get("/index/"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("userEntity"))
                .andExpect(view().name("user-details"));

    }


    @Test
    @DisplayName("Testing index with BDD")
    void indexTestWithBDD(){

        //given
        given(userSession.isLogin()).willReturn(true);
        given(userService.getLoggedUser()).willReturn(new UserEntity());

        //when
        String resultView = indexController.index(model);
        //then

        assertEquals(resultView,"user-details");

        verify(userSession,times(1)).isLogin();
        verify(userSession,never()).logout();
        verify(userSession,never()).getUserEntity();

        verify(userService,times(2)).getLoggedUser();
        verify(userService,never()).findById(anyLong());
        verify(userService,never()).addUser(anyObject());
        verify(userService,never()).logoutUser();
    }

}