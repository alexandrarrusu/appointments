package com.appointment.booking.repository;

import com.appointment.booking.entity.Employee;
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
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeNameById() {
        Employee employee = new Employee.EmployeeBuilder().firstname("Anna").lastname("Stone").company_id(1L).build();
        testEntityManager.persist(employee);
        testEntityManager.flush();
        assertEquals("Anna,Stone", employeeRepository.getEmployeeNameById(employee.getId()));
    }
}
