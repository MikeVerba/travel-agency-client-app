package com.example.travelagencyclientapp.app.models.forms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFormTest {


    RegistrationForm registrationForm;

    @BeforeEach
    void setUp() {

        registrationForm = new RegistrationForm();
    }

    @Test
    void setInvalidId() {

        assertThrows(IllegalArgumentException.class, ()->registrationForm.setId(Integer.valueOf("qwerty")));
        assertThrows(IllegalArgumentException.class, ()->registrationForm.setId(Integer.valueOf("#$***&%^**&^%")));
    }

    @Test
    void setInvalidIdWithLambda() {


        assertAll(

                ()->assertThrows(IllegalArgumentException.class, ()->registrationForm.setId(Integer.valueOf("qwerty"))),
                ()->assertThrows(IllegalArgumentException.class, ()->registrationForm.setId(Integer.valueOf("#$***&%^**&^%"))));
    }

    @Test
    void setInvalidTime() {

        assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("qwerty")));
        assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("#$***&%^**&^%")));
        assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("12-0-56")));
    }

    @Test
    void setInvalidTimeWithLambda() {

        assertAll(
                ()->assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("qwerty"))),
                ()->assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("#$***&%^**&^%"))),
                ()->assertThrows(DateTimeParseException.class,()->registrationForm.setTime(LocalDateTime.parse("12-0-56"))));
    }
}