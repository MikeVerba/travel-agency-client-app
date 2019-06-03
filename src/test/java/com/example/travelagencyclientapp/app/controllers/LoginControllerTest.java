package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserServiceImpl;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    @Mock
    UserSession userSession;

    @InjectMocks
    LoginController loginController;

    @Autowired
    MockMvc mockMvc;

    LoginForm loginForm;

    @BeforeEach
    void setUp() {
        loginForm = new LoginForm();
        loginForm.setUsername("user");
        loginForm.setPassword("pass");

        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @AfterEach
    void tearDown() {
        reset(userService);
        reset(userSession);
        reset(model);
    }

    @Test
    void loginForm() throws Exception {

        mockMvc.perform(get("/login/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("loginForm", instanceOf(LoginForm.class)));
    }

    @Test
    void getUser() {
        //given

        given(userService.login(loginForm)).willReturn(UserServiceImpl.LoginResponse.SUCCESS);

        //when

        String result = loginController.getUser(loginForm,model);


        //then

        then(userService).should().login(ArgumentMatchers.any());

        verify(userService).login(loginForm);

        assertEquals(result,"redirect:/login-success");


    }

    @Test
    void logout() {

        //when

        String result = loginController.logout();


        //then

        verify(userSession).logout();
        assertEquals(result,"logout");
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(get("/login-success/"))
                .andExpect(status().isOk());


    }
}