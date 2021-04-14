package com.appointment.booking.repository;

import com.appointment.booking.entity.Offer_Employee;
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
public class Offer_EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private Offer_EmployeeRepository offer_employeeRepository;

    @Test
    public void getOffersByEmployeeId() {
        Offer_Employee offer_employee1 = new Offer_Employee.Offer_EmployeeBuilder()
                .employee_id(1L).offer_id(1L).build();
        Offer_Employee offer_employee2 = new Offer_Employee.Offer_EmployeeBuilder()
                .employee_id(1L).offer_id(2L).build();
        testEntityManager.persist(offer_employee1);
        testEntityManager.persist(offer_employee2);
        testEntityManager.flush();
        assertEquals(2, offer_employeeRepository.getOffersByEmployeeId(1L).size());
    }
}