package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Employee;
import com.appointment.booking.repository.EmployeeRepository;
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
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @Before
    public void setup() {
        employee = new Employee.EmployeeBuilder().id(1L).firstname("Anna").lastname("Stone").company_id(2L).build();
    }

    @Test
    public void saveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee e = employeeService.saveEmployee(employee);
        assertEquals(Long.valueOf(1L), e.getId());
        assertEquals("Anna", e.getFirstname());
        assertEquals("Stone", e.getLastname());
        assertEquals(Long.valueOf(2L), e.getCompany_id());
    }

    @Test
    public void getEmployeeById(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> o = employeeService.getEmployeeById(1L);
        Employee e = o.get();
        assertEquals(Long.valueOf(1L), e.getId());
        assertEquals("Anna", e.getFirstname());
        assertEquals("Stone", e.getLastname());
        assertEquals(Long.valueOf(2L), e.getCompany_id());
    }

    @Test
    public void getAllEmployees() {
        Employee secondEmployee = new Employee.EmployeeBuilder().id(2L).firstname("Johanna").lastname("Silver")
                .company_id(1L).build();
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(secondEmployee);
        when(employeeRepository.findAll()).thenReturn(list);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    public void getEmployeeNameById() {
        when(employeeRepository.getEmployeeNameById(1L)).thenReturn("Anna,Stone");
        String e = employeeService.getEmployeeNameById(1L);
        assertEquals("Anna,Stone", e);
    }
}
