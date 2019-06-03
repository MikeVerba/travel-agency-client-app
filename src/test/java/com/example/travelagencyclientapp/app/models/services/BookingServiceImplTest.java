package com.example.travelagencyclientapp.app.models.services;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.repositories.OfferRepository;
import com.example.travelagencyclientapp.app.models.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    OfferRepository offerRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BookingServiceImpl bookingService;

    OfferEntity offerEntity;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jan@pazyl.com");
        userEntity.setName("Jan");
        userEntity.setSurname("Pazyl");
        userEntity.setPassword("pass");
        userEntity.setUsername("user");
        userEntity.setAccountStatus(UserEntity.AccountStatus.ACTIVATED);
        userEntity.setTime(LocalDateTime.now());



        offerEntity = new OfferEntity();
        offerEntity.setId(1L);
        offerEntity.setOfferBooked(false);
        offerEntity.setContinent(Continent.AF);
        offerEntity.setDogAllowed(true);
        offerEntity.setNumberOfNights(5);
        offerEntity.setPricePerNight(12.99F);
        offerEntity.setUserEntity(new UserEntity());
    }

    @AfterEach
    void tearDown() {
        reset(offerRepository);
        reset(userRepository);
    }

    @Test
    void bookOfferForUserId() {
        //given



        given(userRepository.findById(anyLong())).willReturn(Optional.of(userEntity));
        given(offerRepository.findById(anyLong())).willReturn(Optional.of(offerEntity));
        given(offerRepository.save(any())).willReturn(offerEntity);

        //when

        OfferEntity returnEntity = bookingService.bookOfferForUserId(1L,1L);

        //then

        then(userRepository).should().findById(anyLong());
        assertTrue(returnEntity.getId() == 1L);


    }

    @Test
    void unbookOfferForOfferId() {

        //given

        given(offerRepository.findById(anyLong())).willReturn(Optional.of(offerEntity));
        given(offerRepository.save(any())).willReturn(offerEntity);

        //when

        OfferEntity returnEntity = bookingService.unbookOfferForOfferId(1L);

        //then


        then(offerRepository).should().findById(anyLong());
        then(offerRepository).should().save(any());

        assertTrue(returnEntity.getId() == 1L);
    }
}