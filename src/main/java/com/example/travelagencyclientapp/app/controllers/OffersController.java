package com.example.travelagencyclientapp.app.controllers;

import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import com.example.travelagencyclientapp.app.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;



@Controller
public class OffersController {

    private final OfferService offerService;
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public OffersController(OfferService offerService, UserService userService, BookingService bookingService) {
        this.offerService = offerService;
        this.userService = userService;
        this.bookingService = bookingService;
    }


    @GetMapping("/all-offers-list")
    public String offersList(Model model){
        model.addAttribute("offerList", offerService.getAllUnbookedOffers());

        return "all-offers-list";
    }


    @GetMapping("/user-chosen-offers")
    public String userChosenOffers(Model model) {

        List<OfferEntity> offerEntityList = offerService.getAllOffersForLoginUser();
        model.addAttribute("chosenOfferList",offerEntityList);


        return "user-chosen-offers";
    }

    @GetMapping("/offer/book-offer/{id}")
    public String bookOffer(@PathVariable("id") Long id){
        bookingService.bookOfferForUserId(userService.getLoggedUser().getId(),id);
        return "redirect:/user-chosen-offers";
    }

    @GetMapping("/offer/unbook-offer/{id}")
    public String unbookOffer(@PathVariable("id") Long id){
        bookingService.unbookOfferForOfferId(id);
        return "redirect:/user-chosen-offers";
    }

    @GetMapping("/search-form")
    public String searchForm(Model model){
        model.addAttribute("offerForm",new OfferForm());
        return "search-form";
    }

    @PostMapping("/search-form")
    public String qualifiedSearchResults(@ModelAttribute("offerForm") @Valid OfferForm offerForm,
                                   BindingResult bindingResult,
                                   Model model){
        if(bindingResult.hasErrors()){
            return "search-form";
        }
        model.addAttribute("searchResults", offerService.getAllOffersQualifiedByConditions(offerForm));
        return "search-form";
    }
}
