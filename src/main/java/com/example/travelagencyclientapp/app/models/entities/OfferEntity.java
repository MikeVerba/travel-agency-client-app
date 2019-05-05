package com.example.travelagencyclientapp.app.models.entities;

import javax.persistence.*;


@Entity
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float pricePerNight;
    private Integer numberOfNights;
    private Continent continent;
    private Boolean isDogAllowed;
    private Boolean isOfferBooked;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
        return isDogAllowed;
    }

    public void setDogAllowed(Boolean dogAllowed) {
        isDogAllowed = dogAllowed;
    }

    public Boolean getOfferBooked() {
        return isOfferBooked;
    }

    public void setOfferBooked(Boolean offerBooked) {
        isOfferBooked = offerBooked;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
