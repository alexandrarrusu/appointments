package com.appointment.booking.controller;

import com.appointment.booking.entity.Offer;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class OfferController {

    private final OfferServiceImpl offerService;

    @Autowired
    public OfferController(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public ResponseEntity<Response<Offer>> saveOffer(@RequestBody Offer offer) {
        offerService.saveOffer(offer);
        return new ResponseEntity<>(new Response<>("Offer added", "201", emptyList()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Offer>> getOfferById(@PathVariable Long id) {
        List<Offer> list = new ArrayList<>();
        Optional<Offer> o = offerService.getOfferById(id);
        if(o.isPresent()) {
            list.add(o.get());
        } else {
            throw new NotFoundException("Offer with id = " + id + " not found");
        }
        return new ResponseEntity<>(new Response<>("Offer with id = " + id, "200", list),
                HttpStatus.OK);
    }

    @RequestMapping(value="/offer", method = RequestMethod.GET)
    public ResponseEntity<Response<Offer>> getAllOffers() {
        return new ResponseEntity<>(new Response<>("Offers found", "200",
                offerService.getAllOffers()), HttpStatus.OK);
    }

}
