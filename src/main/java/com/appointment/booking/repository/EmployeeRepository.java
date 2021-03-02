package com.appointment.booking.repository;

import com.appointment.booking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e.firstname, e.lastname from Employee e where e.id = :id" )
    String getNameById(Long id);
}
