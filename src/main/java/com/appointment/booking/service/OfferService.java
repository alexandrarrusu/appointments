package com.appointment.booking.service;

import com.appointment.booking.entity.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void saveOffer(Offer offer);
    Optional<Offer> getOfferById(Long id);
    List<Offer> getAllOffers();
}
