package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Offer_Employee;
import com.appointment.booking.repository.Offer_EmployeeRepository;
import com.appointment.booking.service.Offer_EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Offer_EmployeeServiceImpl implements Offer_EmployeeService {

    private final Offer_EmployeeRepository offer_employeeRepository;

    public Offer_EmployeeServiceImpl(Offer_EmployeeRepository offer_employeeRepository) {
        this.offer_employeeRepository = offer_employeeRepository;
    }

    @Override
    public void saveOffer_Employee(Offer_Employee offer_employee) {
        offer_employeeRepository.save(offer_employee);
    }

    @Override
    public List<Long> getOffersByEmployeeId(Long id) {
        return offer_employeeRepository.getOffersByEmployeeId(id);
    }
}
