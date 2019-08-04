package com.example.travelagencyclientapp.app.models.forms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferFormTest {

    OfferForm offerForm;

    @BeforeEach
    void setUp() {


        offerForm = new OfferForm();
    }

    @Test
    @DisplayName("Testing Invalid Value In Id Field")
    void setInvalidId() {

        assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("qwerty")));
        assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("12.09993")));
        assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("#$%^**&^%")));


    }


    @Test
    @DisplayName("Testing Invalid Value In Id Field With Lambda")
    void setInvalidIdWithLambda(){

        assertAll(

                () -> assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("qwerty"))),
                () -> assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("12.09993"))),
                () -> assertThrows(IllegalArgumentException.class, ()->offerForm.setId(Long.valueOf("#$%^**&^%"))));
    }


    @Test
    @DisplayName("Testing Invalid Value In PricePerNight Field")
    void setInvalidPricePerNight() {

        assertThrows(IllegalArgumentException.class, ()->offerForm.setPricePerNight(Float.valueOf("qwerty")));
        assertThrows(IllegalArgumentException.class, ()->offerForm.setPricePerNight(Float.valueOf("#$%^**&^%")));
    }


    @Test
    @DisplayName("Testing Invalid Value In PricePerNight Field With Lambda")
    void setInvalidPricePerNightWithLambda() {

        assertAll(

                ()->assertThrows(IllegalArgumentException.class, ()->offerForm.setPricePerNight(Float.valueOf("qwerty"))),
                ()->assertThrows(IllegalArgumentException.class, ()->offerForm.setPricePerNight(Float.valueOf("#$%^**&^%"))));
    }








    @Test
    @DisplayName("Testing Invalid Value In NumberOfNights Field")
    void setInvalidNumberOfNights() {

        assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("qwerty")));
        assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("12.09993")));
        assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("#$%^**&^%")));
    }

    @Test
    @DisplayName("Testing Invalid Value In NumberOfNights Field With Lambda")
    void setInvalidNumberOfNightsWithLambda() {


        assertAll(

                ()->assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("qwerty"))),
                ()->assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("12.09993"))),
                ()->assertThrows(IllegalArgumentException.class, ()->offerForm.setNumberOfNights(Integer.valueOf("#$%^**&^%"))));
    }



}