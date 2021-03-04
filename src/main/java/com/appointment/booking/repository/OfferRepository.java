package com.appointment.booking.repository;

import com.appointment.booking.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o.name from Offer o where o.id = :id")
    String getOfferNameById(Long id);

    @Query("select o.price from Offer o where o.id = :id")
    BigDecimal getOfferPriceById(Long id);
}
