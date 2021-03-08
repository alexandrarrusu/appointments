package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Offer;
import com.appointment.booking.repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    private Offer offer;

    @Before
    public void setup() {
        offer = new Offer.OfferBuilder().id(5L).name("Manicure").price(BigDecimal.valueOf(55.00)).build();
    }

    @Test
    public void saveOffer() {
        when(offerRepository.save(offer)).thenReturn(offer);
        Offer o = offerService.saveOffer(offer);
        assertEquals(offer.getId(), o.getId());
        assertEquals(offer.getName(), o.getName());
        assertEquals(offer.getPrice(), o.getPrice());
    }

    @Test
    public void getOfferById() {
        when(offerRepository.findById(offer.getId())).thenReturn(Optional.of(offer));
        Optional<Offer> optionalOffer = offerService.getOfferById(5L);
        Offer o = optionalOffer.get();
        assertEquals(offer.getId(), o.getId());
        assertEquals(offer.getName(), o.getName());
        assertEquals(offer.getPrice(), o.getPrice());
    }

    @Test
    public void getAllOffers() {
        Offer secondOffer = new Offer.OfferBuilder().id(6L).name("Haircut").price(BigDecimal.valueOf(80.00)).build();
        Offer thirdOffer = new Offer.OfferBuilder().id(7L).name("Pedicure").price(BigDecimal.valueOf(60.00)).build();
        List<Offer> list = new ArrayList<>();
        list.add(offer);
        list.add(secondOffer);
        list.add(thirdOffer);
        when(offerRepository.findAll()).thenReturn(list);
        List<Offer> offers = offerService.getAllOffers();
        assertEquals(list.size(), offers.size());
    }

    @Test
    public void getOfferNameById() {
        when(offerRepository.getOfferNameById(offer.getId())).thenReturn(offer.getName());
        String offerName = offerService.getOfferNameById(offer.getId());
        assertEquals(offer.getName(), offerName);
    }

    @Test
    public void getOfferPriceById() {
        when(offerRepository.getOfferPriceById(offer.getId())).thenReturn(offer.getPrice());
        BigDecimal offerPrice = offerService.getOfferPriceById(offer.getId());
        assertEquals(offer.getPrice(), offerPrice);
    }
}
