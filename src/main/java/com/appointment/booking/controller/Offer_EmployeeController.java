package com.appointment.booking.controller;

import com.appointment.booking.entity.Offer_Employee;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.Offer_EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyList;

@RestController
public class Offer_EmployeeController {

    private final Offer_EmployeeService offer_employeeService;

    public Offer_EmployeeController(Offer_EmployeeService offer_employeeService) {
        this.offer_employeeService = offer_employeeService;
    }

    @RequestMapping(value = "/offer_employee", method = RequestMethod.POST)
    public ResponseEntity<Response<Offer_Employee>> saveOffer_Employee(@RequestBody Offer_Employee offer_employee) {
        offer_employeeService.saveOffer_Employee(offer_employee);
        return new ResponseEntity<>(new Response<>("Offer per employee added", "201", emptyList()),
                HttpStatus.CREATED);
    }
}
