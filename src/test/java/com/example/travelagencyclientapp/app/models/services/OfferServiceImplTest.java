package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import com.example.travelagencyclientapp.app.models.mappers.OfferEntityToOfferFormMapper;
import com.example.travelagencyclientapp.app.models.repositories.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

    @Mock
    OfferRepository offerRepository;

    @Mock
    UserSession userSession;

    @Mock
    OfferEntityToOfferFormMapper offerEntityToOfferFormMapper;

    @InjectMocks
    OfferServiceImpl offerService;

    OfferForm offerForm;

    OfferEntity offerEntity;

    List<OfferEntity> offerEntityList;

    List<OfferForm> offerFormList;

    @BeforeEach
    void setUp() {

        offerEntity = new OfferEntity();
        offerEntity.setId(1L);
        offerEntity.setOfferBooked(false);
        offerEntity.setContinent(Continent.AF);
        offerEntity.setDogAllowed(true);
        offerEntity.setNumberOfNights(5);
        offerEntity.setPricePerNight(12.99F);
        offerEntity.setUserEntity(new UserEntity());

        offerForm = new OfferForm();
        offerForm.setId(1L);
        offerForm.setOfferBooked(false);
        offerForm.setContinent(Continent.AF);
        offerForm.setDogAllowed(true);
        offerForm.setNumberOfNights(5);
        offerForm.setPricePerNight(12.99F);
        offerForm.setUserEntity(new UserEntity());

        offerEntityList = new ArrayList<>();
        offerEntityList.add(offerEntity);

        offerFormList = new ArrayList<>();
        offerFormList.add(offerForm);



    }

    @Test
    void getAllOffers() {

        //given
        given(offerRepository.findAll()).willReturn(offerEntityList);

        //when

        List<OfferEntity> returnList = offerService.getAllOffers();

        //then

        then(offerRepository).should().findAll();
        assertEquals(returnList,offerEntityList);
    }

    @Test
    void getAllOffersForLoginUser() {

        //given
        given(userSession.isLogin()).willReturn(true);
        given(userSession.getUserEntity()).willReturn(new UserEntity());
        given(offerRepository.findByUserEntity(any())).willReturn(offerEntityList);
        //when
        List<OfferEntity> returnList = offerService.getAllOffersForLoginUser();

        //then
        then(userSession).should().isLogin();
        then(userSession).should().getUserEntity();
        then(offerRepository).should().findByUserEntity(any());
        assertEquals(returnList,offerEntityList);

    }

    @Test
    void getAllOffersQualifiedByConditions() {

        //given
        given(offerRepository.findAll()).willReturn(offerEntityList);
        given(offerEntityToOfferFormMapper.map(any())).willReturn(offerForm);


        //when

        List<OfferForm> returnOfferFormList = offerService.getAllOffersQualifiedByConditions(offerForm);

        //then

        then(offerRepository).should().findAll();
        then(offerEntityToOfferFormMapper).should().map(any());
        assertEquals(returnOfferFormList,offerFormList);
    }

    @Test
    void getAllUnbookedOffers() {

        //given

        offerEntity.setUserEntity(null);

        given(offerRepository.findAll()).willReturn(offerEntityList);

        //when
        List<OfferEntity> returnList = offerService.getAllUnbookedOffers();

        //then
        then(offerRepository).should().findAll();
        assertEquals(returnList,offerEntityList);
    }
}