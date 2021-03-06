package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Offer;
import com.appointment.booking.repository.OfferRepository;
import com.appointment.booking.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public String getOfferNameById(Long id) {
        return offerRepository.getOfferNameById(id);
    }

    @Override
    public BigDecimal getOfferPriceById(Long id) {
        return offerRepository.getOfferPriceById(id);
    }
}
