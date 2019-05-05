package com.example.travelagencyclientapp.app.models.services;


import com.example.travelagencyclientapp.app.models.entities.OfferEntity;

public interface BookingService {

    OfferEntity bookOfferForUserId(Long userId, Long offerId);

    OfferEntity unbookOfferForOfferId(Long offerId);
}
