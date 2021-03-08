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
    private List<Company> list = new ArrayList<>();

    @Before
    public void setup() {
        company = new Company.CompanyBuilder().id(1L).name("Company1").address("Stauffacher").build();
    }

    @Test
    public void saveCompany() {
        when(companyRepository.save(company)).thenReturn(company);
        Company c = companyService.saveCompany(company);
        assertEquals(Long.valueOf(1L), c.getId());
    }

    @Test
    public void getAllCompanies() {
        list.add(company);
        Company secondCompany = new Company.CompanyBuilder().id(2L).name("Company2").address("Central").build();
        list.add(secondCompany);
        when(companyRepository.findAll()).thenReturn(list);
        List<Company> companies = companyService.getAllCompanies();
        assertEquals(2, companies.size());
    }

    @Test
    public void getCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Optional<Company> c = companyService.getCompanyById(1L);
        Company com = c.get();
        assertEquals(Long.valueOf(1L), com.getId());
        assertEquals("Company1", com.getName());
        assertEquals("Stauffacher", com.getAddress());
    }

    @Test
    public void getCompanyNameById() {
        when(companyRepository.getCompanyNameById(1L)).thenReturn("Company1");
        String companyName = companyService.getCompanyNameById(1L);
        assertEquals("Company1", companyName);
    }
}
