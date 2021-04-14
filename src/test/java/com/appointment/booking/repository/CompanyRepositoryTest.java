package com.appointment.booking.repository;

import com.appointment.booking.entity.Company;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void getCompanyNameById() {
        Company company = new Company.CompanyBuilder().name("Place1").address("Central").build();
        testEntityManager.persist(company);
        testEntityManager.flush();
        assertEquals(company.getName(), companyRepository.getCompanyNameById(company.getId()));
    }
}