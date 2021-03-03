package com.appointment.booking.repository;

import com.appointment.booking.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c.name from Company c where c.id = :id")
    String getCompanyNameById(Long id);
}
