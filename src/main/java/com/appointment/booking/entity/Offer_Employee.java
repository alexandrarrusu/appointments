package com.appointment.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer_Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employee_id;
    private Long offer_id;

    public Long getId() {
        return id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public Long getOffer_id() {
        return offer_id;
    }
}
