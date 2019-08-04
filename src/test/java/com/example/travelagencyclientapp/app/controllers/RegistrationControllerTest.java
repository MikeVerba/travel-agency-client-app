package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @InjectMocks
    RegistrationController registrationController;

    @Autowired
    MockMvc mockMvc;

    RegistrationForm registrationForm;

    @BeforeEach
    void setUp() {
        registrationForm = new RegistrationForm();
        registrationForm.setId(1);
        registrationForm.setUsername("user");
        registrationForm.setPassword("pass");
        registrationForm.setConfirmPassword("pass");
        registrationForm.setEmail("jan@pazyl.pl");
        registrationForm.setName("Jan");
        registrationForm.setSurname("Pazyl");
        registrationForm.setTime(LocalDateTime.now());

        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @AfterEach
    void tearDown() {
        reset(userService);
        reset(bindingResult);
        reset(model);

    }

    @Test
    @DisplayName("Testing registration form get with Mock Mvc")
    void registrationFormGetTest() throws Exception {

        mockMvc.perform(get("/registration-form/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registrationForm"))
                .andExpect(model().size(1))
                .andExpect(view().name("registration-form"))
                .andExpect(model().attribute("registrationForm", instanceOf(RegistrationForm.class)));
    }

    @Test
    @DisplayName("Testing registration form get with BDD")
    void registrationFormGetTestWithBDD() {

        //given
        given(bindingResult.hasErrors()).willReturn(false);
        given(userService.addUser(registrationForm)).willReturn(true);

        //when

        String resultView = registrationController.registrationForm(registrationForm,bindingResult,model);


        //then

        then(userService).should().addUser(registrationForm);

        verify(bindingResult,times(1)).hasErrors();
        verify(bindingResult,never()).addError(any());
        verify(bindingResult,never()).getModel();
        verify(userService,times(1)).addUser(any());
        verify(userService,never()).findById(anyLong());
        verify(userService,never()).logoutUser();
        verify(userService,never()).updateUser(any());
        verify(userService,never()).login(any());

        assertEquals(resultView,"registration-success");

    }


    @Test
    @Disabled
    @DisplayName("Testing registration form post with Mock Mvc")
    void registrationFormPostTestWithMockMvc() throws Exception{

        given(bindingResult.hasErrors()).willReturn(false);
        given(userService.addUser(registrationForm)).willReturn(true);


        mockMvc.perform(post("/registration-form"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("registration-success"))
                .andExpect(model().attributeExists("registrationForm"))
                .andExpect(status().isOk());

        //todo Circular view path Exception to fix
    }
}