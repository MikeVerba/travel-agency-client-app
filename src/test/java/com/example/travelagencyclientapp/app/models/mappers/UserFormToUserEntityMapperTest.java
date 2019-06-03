package com.example.travelagencyclientapp.app.models.mappers;


import com.example.travelagencyclientapp.app.models.entities.UserEntity;

import com.example.travelagencyclientapp.app.models.forms.RegistrationForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserFormToUserEntityMapperTest {

    UserFormToUserEntityMapper userFormToUserEntityMapper;
    UserEntity userEntity;
    RegistrationForm registrationForm;



    @BeforeEach
    void setUp() {

        userFormToUserEntityMapper = new UserFormToUserEntityMapper();

        registrationForm = new RegistrationForm();
        registrationForm.setId(1);
        registrationForm.setUsername("user");
        registrationForm.setPassword("pass");
        registrationForm.setConfirmPassword("pass");
        registrationForm.setEmail("jan@pazyl.pl");
        registrationForm.setName("Jan");
        registrationForm.setSurname("Pazyl");
        registrationForm.setTime(LocalDateTime.now());

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("user");
        userEntity.setPassword("pass");
        userEntity.setEmail("jan@pazyl.com");
        userEntity.setName("Jan");
        userEntity.setSurname("Pazyl");
        userEntity.setTime(LocalDateTime.now());
        userEntity.setAccountStatus(UserEntity.AccountStatus.ACTIVATED);


    }

    @Test
    void map() {

        UserEntity returnEntity = userFormToUserEntityMapper.map(registrationForm);
        assertEquals(returnEntity.getPassword(),registrationForm.getPassword());
        assertEquals(returnEntity.getName(),registrationForm.getName());


    }
}