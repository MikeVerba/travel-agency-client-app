package com.example.travelagencyclientapp.app.models.mappers;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferEntityToOfferFormMapperTest {

    OfferEntity offerEntity;
    OfferForm offerForm;

    OfferEntityToOfferFormMapper offerEntityToOfferFormMapper;

    @BeforeEach
    void setUp() {

        offerEntityToOfferFormMapper = new OfferEntityToOfferFormMapper();
        offerEntity = new OfferEntity();

        offerEntity.setOfferBooked(false);
        offerEntity.setContinent(Continent.AF);
        offerEntity.setDogAllowed(true);
        offerEntity.setNumberOfNights(5);
        offerEntity.setPricePerNight(12.99F);



        offerForm = new OfferForm();

        offerForm.setOfferBooked(false);
        offerForm.setContinent(Continent.AF);
        offerForm.setDogAllowed(true);
        offerForm.setNumberOfNights(5);
        offerForm.setPricePerNight(12.99F);


    }

    @Test
    @DisplayName(" Testing Non-Equal Properties ")
    void mappingTest() {

        OfferForm resultForm = offerEntityToOfferFormMapper.map(offerEntity);
        assertEquals(resultForm.getContinent(),offerForm.getContinent());
        assertEquals(resultForm.getDogAllowed(),offerForm.getDogAllowed());
        assertEquals(resultForm.getPricePerNight(),offerEntity.getPricePerNight());
        assertEquals(resultForm.getNumberOfNights(),offerEntity.getNumberOfNights());


    }

    @Test
    @DisplayName(" Testing Equal Properties with Lambda Grouping")
    void mappingTestWithLambda() {

        OfferForm resultForm = offerEntityToOfferFormMapper.map(offerEntity);

        assertAll(
                () -> assertEquals(resultForm.getDogAllowed(),offerEntity.getDogAllowed()),
                () ->assertEquals(resultForm.getContinent(),offerEntity.getContinent()),
                () ->assertEquals(resultForm.getPricePerNight(),offerEntity.getPricePerNight()),
                () ->assertEquals(resultForm.getNumberOfNights(),offerEntity.getNumberOfNights()));

    }

    @Test
    @DisplayName(" Testing Non-Equal Properties with Lambda Grouping")
    void mappingTestWithNonEqualValuesWithLambda(){

        OfferForm resultForm = offerEntityToOfferFormMapper.map(offerEntity);

        assertAll(
                () -> assertNotEquals(resultForm.getDogAllowed(),false),
                () -> assertNotEquals(resultForm.getContinent(),Continent.AS),
                () -> assertNotEquals(resultForm.getPricePerNight(),17.90),
                () -> assertNotEquals(resultForm.getNumberOfNights(),8));

    }

    @Test
    @DisplayName(" Testing Non-Equal Properties ")
    void mappingTestWithNonEqualValues(){

        OfferForm resultForm = offerEntityToOfferFormMapper.map(offerEntity);
        assertNotEquals(resultForm.getDogAllowed(),false);
        assertNotEquals(resultForm.getContinent(),Continent.AS);
        assertNotEquals(resultForm.getPricePerNight(),17.90);
        assertNotEquals(resultForm.getNumberOfNights(),8);

    }

}