package com.example.travelagencyclientapp.app.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {


    UserEntity userEntity;

    @BeforeEach
    void setUp() {


        userEntity = new UserEntity();
    }


    @DisplayName("Passing invalid values to field setters")
    @Test
    void testingInvalidValuesPassedToSetters(){


        assertThrows(IllegalArgumentException.class,()-> userEntity.setId(Long.valueOf("12.99943344")));
        assertThrows(DateTimeParseException.class,() ->userEntity.setTime(LocalDateTime.parse("qwerty")));

    }

}