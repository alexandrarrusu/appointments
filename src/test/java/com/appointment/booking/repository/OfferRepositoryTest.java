package com.appointment.booking.repository;

import com.appointment.booking.entity.Offer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class OfferRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OfferRepository offerRepository;

    private Offer offer;

    @Before
    public void setup() {
        offer = new Offer.OfferBuilder().name("Manicure").price(new BigDecimal("55.00")).build();
        testEntityManager.persist(offer);
        testEntityManager.flush();
    }

    @Test
    public void getOfferNameById() {
        assertEquals(offer.getName(), offerRepository.getOfferNameById(offer.getId()));
    }

    @Test
    public void getOfferPriceById() {
        assertEquals(offer.getPrice(), offerRepository.getOfferPriceById(offer.getId()));
    }
}