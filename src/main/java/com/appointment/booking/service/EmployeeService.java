package com.appointment.booking.service;

import com.appointment.booking.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    String getEmployeeNameById(Long id);
}
