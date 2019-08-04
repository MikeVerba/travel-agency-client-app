package com.example.travelagencyclientapp.app.controllers;


import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.services.UserService;
import com.example.travelagencyclientapp.app.models.services.UserServiceImpl;
import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

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
    @DisplayName("Testing Get Request on Login Controller")
    void loginFormTest() throws Exception {

        mockMvc.perform(get("/login/"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("loginForm"))
                .andExpect(view().name("login"))
                .andExpect(model().size(1))
                .andExpect(model().attribute("loginForm", instanceOf(LoginForm.class)));
    }


    @Test
    @DisplayName("Testing Get Request with invalid template name")
    void loginFormTestInvalidTemplateName() throws Exception{
        mockMvc.perform(get("qwerty"))
                .andExpect(status().is4xxClientError());
    }




    @Test
    @DisplayName("Testing getUser with BDD")
    void getUserTest() {
        //given

        given(userService.login(loginForm)).willReturn(UserServiceImpl.LoginResponse.SUCCESS);

        //when

        String result = loginController.getUser(loginForm,bindingResult);


        //then

        assertEquals(result,"redirect:/login-success");


    }

    @Test
    @DisplayName("Testing Post Mapping getUser with Mock Mvc")
    void getUserTestWithMockMvc() throws Exception{

        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("user");
        loginForm.setPassword("pass");

        mockMvc.perform(post("/login")
        .param("username","user")
        .param("password","pass"))
                .andExpect(status().is3xxRedirection());

    }



    @Test
    @DisplayName("Testing Logout without MockMvc")
    void logoutTest() {

        //when

        String result = loginController.logout();


        //then

        verify(userSession,times(1)).logout();
        verify(userSession,never()).isLogin();
        verify(userSession,never()).setLogin(anyBoolean());
        verify(userSession,never()).getUserEntity();
        verify(userSession,never()).setUserEntity(anyObject());
        assertEquals(result,"logout");
    }


    @Test
    @Disabled
    @DisplayName("Testing Logout with MockMvc")
    void  logoutTestWithMockMvc() throws Exception{

        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection());
        //todo getting circular view path exception

    }




    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(get("/login-success/"))
                .andExpect(status().isOk());


    }
}