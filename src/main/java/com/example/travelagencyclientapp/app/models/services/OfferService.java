package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;

import java.util.List;

public interface OfferService {

    List<OfferEntity> getAllOffers();

    List<OfferEntity> getAllOffersForLoginUser();


    List<OfferForm> getAllOffersQualifiedByConditions(OfferForm conditionsOfferDto);


    List<OfferEntity> getAllUnbookedOffers();
}
