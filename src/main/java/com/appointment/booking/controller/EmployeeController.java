package com.appointment.booking.controller;

import com.appointment.booking.entity.Client;
import com.appointment.booking.entity.Employee;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Response<Employee>> saveEmployee(@RequestBody Employee employee) {
        List<Employee> list = new ArrayList<>();
        list.add(employeeService.saveEmployee(employee));
        return new ResponseEntity<>(new Response<>("Employee added", "201", list),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Employee>> getEmployeeById(@PathVariable Long id) {
        List<Employee> list = new ArrayList<>();
        Optional<Employee> e = employeeService.getEmployeeById(id);
        if(e.isPresent()) {
            list.add(e.get());
        } else {
            throw new NotFoundException("Employee with id = " + id + " not found");
        }
        return new ResponseEntity<>(new Response<>("Employee with id = " + id, "200", list),
                HttpStatus.OK);
    }


    @RequestMapping(value="/employee", method = RequestMethod.GET)
    public ResponseEntity<Response<Employee>> getAllEmployees() {
        return new ResponseEntity<>(new Response<>("Employees found", "200",
                employeeService.getAllEmployees()), HttpStatus.OK);
    }
}
