package com.example.travelagencyclientapp.app.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferEntityTest {


    OfferEntity offerEntity;


    @BeforeEach
    void setUp(){

        offerEntity = new OfferEntity();
    }


    @DisplayName("Passing invalid values to field setters")
    @Test
    void testingInvalidValuesPassedToSetters(){


        assertThrows(IllegalArgumentException.class,() ->offerEntity.setPricePerNight(Float.valueOf("asdf")));
        assertThrows(IllegalArgumentException.class,() ->offerEntity.setNumberOfNights(Integer.valueOf("zxcvb")));
        assertThrows(IllegalArgumentException.class,() ->offerEntity.setNumberOfNights(Integer.valueOf("12.90")));
        assertThrows(IllegalArgumentException.class,()-> offerEntity.setId(Long.valueOf("12.99943344")));

    }
}