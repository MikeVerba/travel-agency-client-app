package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserSessionTest {


    UserEntity userEntity;


    UserSession userSession;

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

        userSession = new UserSession();
    }

    @Test
    void logout() {
        userSession.logout();
        assertFalse(userSession.isLogin());
        assertNull(userSession.getUserEntity());
    }
}