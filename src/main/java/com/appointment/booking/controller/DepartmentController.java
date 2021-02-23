package com.appointment.booking.controller;

import com.appointment.booking.controller.response.Response;
import com.appointment.booking.entity.Department;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public ResponseEntity<Response<Department>> saveDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return ResponseEntity.ok().body(new Response<>("Department was added", 201, emptyList()));
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ResponseEntity<Response<Department>> getAllDepartments() {
        return ResponseEntity.ok().body(new Response<>("Departments found", 200,
                departmentService.getAllDepartments()));
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Department>> getDepartmentById(@PathVariable Long id) {
        List<Department> list = new ArrayList<>();
        Optional<Department> department = departmentService.getDepartmentById(id);
        if(department.isPresent()) {
            list.add(department.get());
        } else {
            throw new NotFoundException("Department not found");
        }
        return ResponseEntity.ok().body(new Response<>("Department with id = " + id, 200, list));
    }
}
