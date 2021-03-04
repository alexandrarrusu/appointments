package com.appointment.booking.repository;

import com.appointment.booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.client_id = :id")
    List<Appointment> getAppointmentsByClientId(Long id);

    @Query("select a from Appointment a where a.employee_id = :id")
    List<Appointment> getAppointmentsByEmployeeId(Long id);
}
