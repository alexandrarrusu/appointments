package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Company;
import com.appointment.booking.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company company;

    @Before
    public void setup() {
        company = new Company.CompanyBuilder().id(1L).name("Company1").address("Stauffacher").build();
    }

    @Test
    public void saveCompany() {
        when(companyRepository.save(company)).thenReturn(company);
        Company c = companyService.saveCompany(company);
        assertEquals(company.getId(), c.getId());
    }

    @Test
    public void getAllCompanies() {
        Company secondCompany = new Company.CompanyBuilder().id(2L).name("Company2").address("Central").build();
        List<Company> list = new ArrayList<>();
        list.add(company);
        list.add(secondCompany);
        when(companyRepository.findAll()).thenReturn(list);
        List<Company> companies = companyService.getAllCompanies();
        assertEquals(list.size(), companies.size());
    }

    @Test
    public void getCompanyById() {
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        Optional<Company> c = companyService.getCompanyById(company.getId());
        Company com = c.get();
        assertEquals(company.getId(), com.getId());
        assertEquals(company.getName(), com.getName());
        assertEquals(company.getAddress(), com.getAddress());
    }

    @Test
    public void getCompanyNameById() {
        when(companyRepository.getCompanyNameById(company.getId())).thenReturn(company.getName());
        String companyName = companyService.getCompanyNameById(company.getId());
        assertEquals(company.getName(), companyName);
    }
}
