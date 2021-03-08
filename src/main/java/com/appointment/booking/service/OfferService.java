package com.appointment.booking.service;

import com.appointment.booking.entity.Offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OfferService {

    Offer saveOffer(Offer offer);
    Optional<Offer> getOfferById(Long id);
    List<Offer> getAllOffers();
    String getOfferNameById(Long id);
    BigDecimal getOfferPriceById(Long id);
}
