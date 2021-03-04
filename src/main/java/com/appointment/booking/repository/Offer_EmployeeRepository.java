package com.appointment.booking.repository;

import com.appointment.booking.entity.Offer_Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Offer_EmployeeRepository extends JpaRepository<Offer_Employee, Long> {

    @Query("select o.offer_id from Offer_Employee o where o.employee_id = :id")
    List<Long> getOffersByEmployeeId(Long id);
}
