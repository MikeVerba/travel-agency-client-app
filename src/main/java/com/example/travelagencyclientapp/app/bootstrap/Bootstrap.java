package com.example.travelagencyclientapp.app.bootstrap;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.OfferRepository;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import com.example.travelagencyclientapp.app.models.services.BookingService;
import com.example.travelagencyclientapp.app.models.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final HashService hashService;

    @Autowired
    public Bootstrap(UserRepository userRepository, OfferRepository offerRepository,  HashService hashService) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.hashService = hashService;
    }

    @Override
    public void run(String... args) throws Exception {

        loadOfferData();
        loadUserData();

    }

    private void loadOfferData(){

        OfferEntity offer1 = new OfferEntity();
        offer1.setNumberOfNights(2);
        offer1.setPricePerNight(219.99F);
        offer1.setContinent(Continent.AF);
        offer1.setDogAllowed(false);
        offer1.setOfferBooked(false);
        offerRepository.save(offer1);

        OfferEntity offer2 = new OfferEntity();
        offer2.setNumberOfNights(4);
        offer2.setPricePerNight(300.99F);
        offer2.setContinent(Continent.EU);
        offer2.setDogAllowed(true);
        offer2.setOfferBooked(false);
        offerRepository.save(offer2);

        OfferEntity offer3 = new OfferEntity();
        offer3.setNumberOfNights(7);
        offer3.setPricePerNight(500.99F);
        offer3.setContinent(Continent.AS);
        offer3.setDogAllowed(false);
        offer3.setOfferBooked(false);
        offerRepository.save(offer3);

        OfferEntity offer4 = new OfferEntity();
        offer4.setNumberOfNights(6);
        offer4.setPricePerNight(260.99F);
        offer4.setContinent(Continent.AF);
        offer4.setDogAllowed(true);
        offer4.setOfferBooked(false);
        offerRepository.save(offer4);

        System.out.println("Offer Data loaded: " + offerRepository.count());

    }

    private void loadUserData(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Jan");
        userEntity.setSurname("Pazyl");
        userEntity.setEmail("jan@op.pl");
        userEntity.setUsername("user");
        userEntity.setPassword(hashService.hashPassword("pass"));
        userRepository.save(userEntity);
        System.out.println("User Data loaded: " + userRepository.count());
    }


}
