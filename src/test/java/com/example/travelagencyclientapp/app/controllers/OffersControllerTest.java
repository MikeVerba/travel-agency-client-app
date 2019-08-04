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
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("Testing getting unbooked offers with BDD")
    void offersListBDD() {

        //given

        given(offerService.getAllUnbookedOffers()).willReturn(offerEntityList);

        //when

        String resultView = offersController.offersList(model);

        //then

        then(offerService).should().getAllUnbookedOffers();
        assertEquals(resultView,"all-offers-list");
    }

    @Test
    @DisplayName("Testing getting all offers with Mock Mvc")
    void offersListWithMockMvc() throws Exception {

        mockMvc.perform(get("/all-offers-list/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("offerList"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1))
                .andExpect(model().attribute("offerList",instanceOf(List.class)));
    }

    @Test
    @DisplayName("Testing getting chosen offers with BDD")
    void userChosenOffersBDD() {

        //given
        given(offerService.getAllOffersForLoginUser()).willReturn(offerEntityList);

        //when

        String resultView = offersController.userChosenOffers(model);

        //then
        then(offerService).should().getAllOffersForLoginUser();
        verify(offerService,times(1)).getAllOffersForLoginUser();
        verify(offerService,never()).getAllOffers();
        verify(offerService,never()).getAllUnbookedOffers();
        assertEquals(resultView,"user-chosen-offers");


    }

    @Test
    @DisplayName("Testing getting chosen offers with Mock Mvc")
    void userChosenOffersMockMvc() throws Exception {
        mockMvc.perform(get("/user-chosen-offers/"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("chosenOfferList"))
                .andExpect(model().attribute("chosenOfferList",instanceOf(List.class)));
    }

    @Test
    @DisplayName("Testing booking offer with BDD")
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
        verify(bookingService,times(1)).bookOfferForUserId(anyLong(),anyLong());
        verify(bookingService, never()).unbookOfferForOfferId(anyLong());
        verify(userService,times(1)).getLoggedUser();
        verify(userService,never()).login(anyObject());
        verify(userService,never()).addUser(anyObject());
        verify(userService,never()).logoutUser();
        verify(userService,never()).findById(anyLong());

        assertEquals(resultView,"redirect:/user-chosen-offers");

    }

    @Test
    @DisplayName("Testing booking offer with Mock Mvc")
    void bookOfferMockMvc() throws Exception{

        UserEntity userEntity = new UserEntity();

        given(userService.getLoggedUser()).willReturn(userEntity);


        mockMvc.perform(get("/offer/book-offer/"+1L))
                .andExpect(view().name("redirect:/user-chosen-offers"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }




    @Test
    @DisplayName("Testing Unbooking offer with BDD")
    void unBookOfferBDD() {

        //given

        given(bookingService.unbookOfferForOfferId(anyLong())).willReturn(new OfferEntity());

        //when

        String resultView = offersController.unbookOffer(1L);

        //then

        then(bookingService).should().unbookOfferForOfferId(anyLong());

        verify(bookingService, times(1)).unbookOfferForOfferId(anyLong());
        verify(bookingService, never()).bookOfferForUserId(anyLong(),anyLong());
        assertEquals(resultView,"redirect:/user-chosen-offers");
    }

    @Test
    @DisplayName("Testing Unbooking offer with Mock Mvc")
    void unBookOfferMockMvc() throws Exception{

        given(bookingService.unbookOfferForOfferId(anyLong())).willReturn(new OfferEntity());

        mockMvc.perform(get("/offer/unbook-offer/"+1L))
                .andExpect(view().name("redirect:/user-chosen-offers"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(0))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Testing search form with Mock Mvc")
    void searchFormTestMockMvc() throws Exception {

        mockMvc.perform(get("/search-form/"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-form"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("offerForm"))
                .andExpect(model().attribute("offerForm",instanceOf(OfferForm.class)));
    }

    @Test
    @DisplayName("Testing search results with BDD")
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
        verify(offerService,times(1)).getAllOffersQualifiedByConditions(anyObject());
        verify(offerService,never()).getAllOffers();
        verify(offerService,never()).getAllOffersForLoginUser();
        verify(offerService,never()).getAllUnbookedOffers();

        assertEquals(resultView,"redirect:/search-form");
    }

    @Test
    @DisplayName("Testing search results with Mock Mvc")
    void qualifiedSearchResultsMockMvc() throws Exception{


        given(offerService.getAllOffersQualifiedByConditions(anyObject())).willReturn(new ArrayList<>());

        mockMvc.perform(post("/search-form"))
                .andExpect(model().attributeExists("searchResults"))
                .andExpect(model().size(2))
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());

    }


}