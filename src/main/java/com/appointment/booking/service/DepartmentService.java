package com.appointment.booking.service;

import com.appointment.booking.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    void saveDepartment(Department department);
    Optional<Department> getDepartmentById(Long id);
    List<Department> getAllDepartments();
}
