package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import com.example.travelagencyclientapp.app.models.mappers.OfferEntityToOfferFormMapper;
import com.example.travelagencyclientapp.app.models.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final UserSession userSession;
    private final OfferEntityToOfferFormMapper offerEntityToOfferFormMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserSession userSession, OfferEntityToOfferFormMapper offerEntityToOfferFormMapper) {
        this.offerRepository = offerRepository;
        this.userSession = userSession;
        this.offerEntityToOfferFormMapper = offerEntityToOfferFormMapper;
    }


    @Override
    public List<OfferEntity> getAllOffers() {

        return new ArrayList<>(offerRepository.findAll());
    }

    @Override
    public List<OfferEntity> getAllOffersForLoginUser(){
        if(!userSession.isLogin()){
            throw new IllegalStateException("user not login");
        }
        return offerRepository.findByUserEntity(userSession.getUserEntity());
    }


    @Override
    public List<OfferForm> getAllOffersQualifiedByConditions(OfferForm conditionsOfferForm) {
        return offerRepository.findAll()
                .stream()
                .filter(offer -> !offer.getOfferBooked())
                .map(offerEntity -> {
                    OfferForm offerForm = offerEntityToOfferFormMapper.map(offerEntity);
                    offerForm.setId(offerEntity.getId());
                    return offerForm;
                })
                .filter(offerForm -> {

                    return offerForm.getPricePerNight() <= conditionsOfferForm.getPricePerNight() ||
                            offerForm.getNumberOfNights() >= conditionsOfferForm.getNumberOfNights() ||
                            offerForm.getContinent().equals(conditionsOfferForm.getContinent()) ||
                            offerForm.getDogAllowed().equals(conditionsOfferForm.getDogAllowed());

                })
                .collect(Collectors.toList());
    }


    @Override
    public List<OfferEntity> getAllUnbookedOffers() {

        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getUserEntity() == null)
                .collect(Collectors.toList());
    }
}
