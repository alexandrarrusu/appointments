package com.appointment.booking.service;

import com.appointment.booking.entity.Offer_Employee;

import java.util.List;

public interface Offer_EmployeeService {

    void saveOffer_Employee(Offer_Employee offer_employee);
    List<Long> getOffersByEmployeeId(Long id);
}
