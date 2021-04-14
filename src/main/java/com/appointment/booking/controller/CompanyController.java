package com.appointment.booking.controller;

import com.appointment.booking.entity.Company;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity<Response<Company>> saveCompany(@RequestBody Company company) {
        List<Company> list = new ArrayList<>();
        list.add(companyService.saveCompany(company));
        return new ResponseEntity<>(new Response<>("Company added", "201", list),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Company>> getCompanyById(@PathVariable Long id) {
        List<Company> list = new ArrayList<>();
        Optional<Company> p = companyService.getCompanyById(id);
        if (p.isPresent()) {
            list.add(p.get());
        } else {
            throw new NotFoundException("Company with id = " + id + " not found");
        }
        return new ResponseEntity<>(new Response<>("Company with id = " + id, "200", list),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public ResponseEntity<Response<Company>> getAllCompanies() {
        return new ResponseEntity<>(new Response<>("Companies found", "200",
                companyService.getAllCompanies()), HttpStatus.OK);
    }
}
