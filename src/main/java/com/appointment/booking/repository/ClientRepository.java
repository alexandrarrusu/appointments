package com.appointment.booking.repository;

import com.appointment.booking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c.email from Client c where c.id = :id" )
    String getEmailById(Long id);
}
