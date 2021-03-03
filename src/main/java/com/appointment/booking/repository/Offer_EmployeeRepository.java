package com.appointment.booking.repository;

import com.appointment.booking.entity.Offer_Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Offer_EmployeeRepository extends JpaRepository<Offer_Employee, Long> {
}
