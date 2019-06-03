package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    UserService userService;

    @Mock
    UserSession userSession;

    @InjectMocks
    IndexController indexController;

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
    void index() throws Exception{


        MvcResult mvcResult = mockMvc.perform(get("/index/"))
                .andExpect(status().isOk())
                .andReturn();


    }

}