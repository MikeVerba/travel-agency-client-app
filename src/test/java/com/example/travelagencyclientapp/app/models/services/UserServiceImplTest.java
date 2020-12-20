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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

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
        given(userRepository.getUserByUsername(anyString())).willReturn(Optional.of(userEntity));
        given(hashService.isPasswordCorrect(anyString(),anyString())).willReturn(true);


        //when

        UserServiceImpl.LoginResponse loginResponse = userService.login(loginForm);

        //then
        then(userRepository).should().getUserByUsername(loginForm.getUsername());
        then(hashService).should().isPasswordCorrect(anyString(),anyString());
        assertEquals(loginResponse, UserServiceImpl.LoginResponse.SUCCESS);
    }

    @Test
    void findById() {
        //given
        given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));

        //when
        UserEntity returnEntity = userService.findById(1L);

        //then

        then(userRepository).should().findById(1L);
        assertEquals(returnEntity,userEntity);
    }

    @Test
    void logoutUser() {

        //when
        userService.logoutUser();

        //then
        verify(userSession).setLogin(false);
    }

    @Test
    void updateUser() {

        //when
        userService.updateUser(userEntity);

        //then
        verify(userRepository).save(any());
    }

    @Test
    void getLoggedUser() {
        //given
        given(userSession.getUserEntity()).willReturn(userEntity);

        //when
        UserEntity returnUserEntity = userService.getLoggedUser();

        //then
        verify(userSession).getUserEntity();
        then(userSession).should().getUserEntity();
        assertEquals(returnUserEntity,userEntity);
    }
}