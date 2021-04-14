package com.appointment.booking.controller;

import com.appointment.booking.entity.Offer;
import com.appointment.booking.entity.Offer_Employee;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.Offer_EmployeeService;
import com.appointment.booking.service.impl.OfferServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Offer_EmployeeController {

    private final Offer_EmployeeService offer_employeeService;
    private final OfferServiceImpl offerService;

    public Offer_EmployeeController(Offer_EmployeeService offer_employeeService,
                                    OfferServiceImpl offerService) {
        this.offer_employeeService = offer_employeeService;
        this.offerService = offerService;
    }

    @RequestMapping(value = "/offer_employee", method = RequestMethod.POST)
    public ResponseEntity<Response<Offer_Employee>> saveOffer_Employee(@RequestBody Offer_Employee offer_employee) {
        List<Offer_Employee> list = new ArrayList<>();
        list.add(offer_employeeService.saveOffer_Employee(offer_employee));
        return new ResponseEntity<>(new Response<>("Offer per employee added", "201", list),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/offer_employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Offer>> getOffersByEmployeeId(@PathVariable Long id) {
        List<Long> offersId = offer_employeeService.getOffersByEmployeeId(id);
        List<Optional<Offer>> offers = new ArrayList<>();
        for (Long l : offersId) {
            offers.add(offerService.getOfferById(l));
        }
        return new ResponseEntity<>(new Response<>("Offers found for employee with id = " + id, "200",
                offers.stream().map(Optional::get).collect(Collectors.toList())), HttpStatus.OK);
    }
}
