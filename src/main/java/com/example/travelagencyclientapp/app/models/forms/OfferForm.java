package com.example.travelagencyclientapp.app.models.forms;

import com.example.travelagencyclientapp.app.models.entities.Continent;
import com.example.travelagencyclientapp.app.models.entities.UserEntity;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;



public class OfferForm {

    private Long id;

    @DecimalMax("100000.0") @DecimalMin("0.0")
    private Float pricePerNight;

    @DecimalMax("100.0") @DecimalMin("0.0")
    private Integer numberOfNights;

    private Continent continent;
    private Boolean dogAllowed;
    private Boolean offerBooked;
    private UserEntity userEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Boolean getDogAllowed() {
        return dogAllowed;
    }

    public void setDogAllowed(Boolean dogAllowed) {
        this.dogAllowed = dogAllowed;
    }

    public Boolean getOfferBooked() {
        return offerBooked;
    }

    public void setOfferBooked(Boolean offerBooked) {
        this.offerBooked = offerBooked;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
