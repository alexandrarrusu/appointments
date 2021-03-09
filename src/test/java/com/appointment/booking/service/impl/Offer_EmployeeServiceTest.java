package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Offer_Employee;
import com.appointment.booking.repository.Offer_EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class Offer_EmployeeServiceTest {

    @Mock
    private Offer_EmployeeRepository offer_employeeRepository;

    @InjectMocks
    private Offer_EmployeeServiceImpl offer_employeeService;

    private Offer_Employee offer_employee;

    @Before
    public void setup() {
        offer_employee = new Offer_Employee.Offer_EmployeeBuilder()
                .id(1L).employee_id(1L).offer_id(2L).build();
    }

    @Test
    public void saveOffer_Employee() {
        when(offer_employeeRepository.save(offer_employee)).thenReturn(offer_employee);
        Offer_Employee o_e = offer_employeeService.saveOffer_Employee(offer_employee);
        assertEquals(offer_employee.getId(), o_e.getId());
        assertEquals(offer_employee.getEmployee_id(), o_e.getEmployee_id());
        assertEquals(offer_employee.getOffer_id(), o_e.getOffer_id());
    }

    @Test
    public void getOffersByEmployeeId() {
        Offer_Employee secondOffer_Employee = new Offer_Employee.Offer_EmployeeBuilder()
                .id(2L).employee_id(offer_employee.getEmployee_id()).offer_id(5L).build();
        Offer_Employee thirdOffer_Employee = new Offer_Employee.Offer_EmployeeBuilder()
                .id(3L).employee_id(offer_employee.getEmployee_id()).offer_id(5L).build();
        List<Long> list = new ArrayList<>();
        list.add(offer_employee.getOffer_id());
        list.add(secondOffer_Employee.getOffer_id());
        list.add(thirdOffer_Employee.getOffer_id());
        when(offer_employeeRepository.getOffersByEmployeeId(offer_employee.getId())).thenReturn(list);
        List<Long> offersId = offer_employeeService.getOffersByEmployeeId(offer_employee.getId());
        assertEquals(list.size(), offersId.size());
    }
}
