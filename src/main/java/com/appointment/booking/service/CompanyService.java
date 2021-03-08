package com.appointment.booking.service;

import com.appointment.booking.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company saveCompany(Company company);
    Optional<Company> getCompanyById(Long id);
    List<Company> getAllCompanies();
    String getCompanyNameById(Long id);
}
