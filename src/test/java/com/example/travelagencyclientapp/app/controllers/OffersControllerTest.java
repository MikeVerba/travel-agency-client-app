package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import com.example.travelagencyclientapp.app.models.services.BookingService;
import com.example.travelagencyclientapp.app.models.services.OfferService;
import com.example.travelagencyclientapp.app.models.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OffersControllerTest {

    @Mock
    OfferService offerService;

    @Mock
    UserService userService;

    @Mock
    BookingService bookingService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OffersController offersController;

    @Autowired
    MockMvc mockMvc;

    OfferForm offerForm;

    List<OfferEntity> offerEntityList;



    @BeforeEach
    void setUp() {

        offerEntityList = new ArrayList<>();
        offerEntityList.add(new OfferEntity());

        offerForm = new OfferForm();
        offerForm.setContinent(Continent.AF);
        offerForm.setDogAllowed(true);
        offerForm.setId(1L);
        offerForm.setNumberOfNights(3);
        offerForm.setPricePerNight(12.99F);
        offerForm.setOfferBooked(false);
        offerForm.setUserEntity(new UserEntity());

        mockMvc = MockMvcBuilders.standaloneSetup(offersController).build();

    }

    @AfterEach
    void tearDown() {
        reset(offerService);
        reset(userService);
        reset(bookingService);
        reset(model);
        reset(bindingResult);
    }

    @Test
    void offersListBDD() {

        //given

        given(offerService.getAllUnbookedOffers()).willReturn(offerEntityList);

        //when

        String result = offersController.offersList(model);

        //then

        then(offerService).should().getAllUnbookedOffers();
        assertEquals(result,"all-offers-list");
    }

    @Test
    void offersListWithMockMvc() throws Exception {

        mockMvc.perform(get("/all-offers-list/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("offerList",instanceOf(List.class)));
    }

    @Test
    void userChosenOffersBDD() {

        //given
        given(offerService.getAllOffersForLoginUser()).willReturn(offerEntityList);

        //when

        String resultView = offersController.userChosenOffers(model);

        //then
        then(offerService).should().getAllOffersForLoginUser();
        assertEquals(resultView,"user-chosen-offers");


    }

    @Test
    void userChosenOffersMockMvc() throws Exception {
        mockMvc.perform(get("/user-chosen-offers/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("chosenOfferList",instanceOf(List.class)));
    }

    @Test
    void bookOfferBDD() {

        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        given(bookingService.bookOfferForUserId(anyLong(),anyLong())).willReturn(new OfferEntity());
        given(userService.getLoggedUser()).willReturn(userEntity);

        //when

        String resultView = offersController.bookOffer(1L);

        //then

        then(bookingService).should().bookOfferForUserId(anyLong(),anyLong());
        then(userService).should().getLoggedUser();

        assertEquals(resultView,"redirect:/user-chosen-offers");

    }


    @Test
    void unbookOfferBDD() {

        //given

        given(bookingService.unbookOfferForOfferId(anyLong())).willReturn(new OfferEntity());

        //when

        String resultView = offersController.unbookOffer(1L);

        //then

        then(bookingService).should().unbookOfferForOfferId(anyLong());
        assertEquals(resultView,"redirect:/user-chosen-offers");
    }

    @Test
    void searchForm() throws Exception {

        mockMvc.perform(get("/search-form/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("offerForm",instanceOf(OfferForm.class)));

    }

    @Test
    void qualifiedSearchResultsBDD() {

        List<OfferForm> offerFormList = new ArrayList<>();
        offerFormList.add(new OfferForm());

        //given
        given(offerService.getAllOffersQualifiedByConditions(ArgumentMatchers.any())).willReturn(offerFormList);
        given(bindingResult.hasErrors()).willReturn(false);

        //when

        String resultView = offersController.qualifiedSearchResults(offerForm,bindingResult,model);

        //then

        then(offerService).should().getAllOffersQualifiedByConditions(offerForm);
        then(bindingResult).should().hasErrors();

        assertEquals(resultView,"search-form");
    }
}