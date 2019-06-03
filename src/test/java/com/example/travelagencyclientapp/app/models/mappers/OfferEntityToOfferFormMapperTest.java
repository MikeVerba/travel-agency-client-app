package com.example.travelagencyclientapp.app.models.mappers;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import org.junit.jupiter.api.BeforeEach;
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
    void map() {

        OfferForm resultForm = offerEntityToOfferFormMapper.map(offerEntity);
        assertEquals(resultForm.getContinent(),offerForm.getContinent());
        assertEquals(resultForm.getDogAllowed(),offerForm.getDogAllowed());

    }
}