package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.LoginForm;
import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import com.example.travelagencyclientapp.app.models.mappers.UserFormToUserEntityMapper;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserSession userSession;

    @Mock
    HashService hashService;

    @InjectMocks
    UserServiceImpl userService;

    RegistrationForm registrationForm;

    LoginForm loginForm;

    UserEntity userEntity;

    @Mock
    UserFormToUserEntityMapper userFormToUserEntityMapper;




    @BeforeEach
    void setUp() {

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jan@pazyl.com");
        userEntity.setName("Jan");
        userEntity.setSurname("Pazyl");
        userEntity.setPassword("pass");
        userEntity.setUsername("user");
        userEntity.setAccountStatus(UserEntity.AccountStatus.ACTIVATED);
        userEntity.setTime(LocalDateTime.now());

        loginForm = new LoginForm();
        loginForm.setUsername("user");
        loginForm.setPassword("pass");

        registrationForm = new RegistrationForm();
        registrationForm.setId(1);
        registrationForm.setUsername("user");
        registrationForm.setPassword("pass");
        registrationForm.setConfirmPassword("pass");
        registrationForm.setEmail("jan@pazyl.pl");
        registrationForm.setName("Jan");
        registrationForm.setSurname("Pazyl");
        registrationForm.setTime(LocalDateTime.now());


    }

//    @Test
//    void addUser() {
//
//        //given
//        given(userRepository.save(any())).willReturn(registrationForm);
//        given(hashService.hashPassword(anyString())).willReturn("pass");
//        given(userFormToUserEntityMapper.map(any())).willReturn(userEntity);
//
//        //when
//        userService.addUser(registrationForm);
//        //then
//        then(userRepository).should().save(any());
//        //assertTrue(result);
//        assertTrue(registrationForm.getPassword().equals("pass"));
//    }

    @Test
    void login() {
        //given


        //when

        //then
    }

    @Test
    void findById() {
        //given

        //when

        //then
    }

    @Test
    void logoutUser() {
        //given

        //when

        //then
    }

    @Test
    void updateUser() {
        //given

        //when

        //then
    }

    @Test
    void getLoggedUser() {
        //given

        //when

        //then
    }
}