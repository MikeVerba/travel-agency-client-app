package com.example.travelagencyclientapp.app.models.mappers;

import com.example.travelagencyclientapp.app.models.entities.OfferEntity;
import com.example.travelagencyclientapp.app.models.forms.OfferForm;
import org.springframework.stereotype.Service;

@Service
public class OfferEntityToOfferFormMapper extends Mapper<OfferEntity, OfferForm> {
    @Override
    public OfferForm map(OfferEntity key) {
        OfferForm offerForm = new OfferForm();
        offerForm.setContinent(key.getContinent());
        offerForm.setDogAllowed(key.getDogAllowed());
        offerForm.setNumberOfNights(key.getNumberOfNights());
        offerForm.setPricePerNight(key.getPricePerNight());

        return offerForm;
    }
}
