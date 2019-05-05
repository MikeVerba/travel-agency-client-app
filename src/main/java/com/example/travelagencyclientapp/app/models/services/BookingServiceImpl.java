package com.example.travelagencyclientapp.app.models.services;


import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.OfferRepository;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final OfferServiceImpl offerServiceImpl;
    private final UserServiceImpl clientService;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;


    @Autowired
    public BookingServiceImpl(OfferServiceImpl offerServiceImpl,
                              UserServiceImpl clientService,
                              OfferRepository offerRepository,
                              UserRepository userRepository) {

        this.offerServiceImpl = offerServiceImpl;
        this.clientService = clientService;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;


    }

    @Override
    public OfferEntity bookOfferForUserId(Long userId, Long offerId) {



        UserEntity userEntity = userRepository.findById(userId).get();

        return offerRepository.findById(offerId)
                .map(offer -> {

                    offer.setOfferBooked(true);
                    offer.setUserEntity(userEntity);
                    OfferEntity savedOffer = offerRepository.save(offer);
                    return savedOffer;
                })
                .orElseThrow(RuntimeException::new);

    }

    @Override
    public OfferEntity unbookOfferForOfferId(Long offerId) {


        return offerRepository.findById(offerId)
                .map(offer -> {
                    offer.setOfferBooked(false);
                    offer.setUserEntity(null);
                    OfferEntity unbookedOffer = offerRepository.save(offer);
                    return unbookedOffer;
                } ).orElseThrow(RuntimeException::new);

    }


}
