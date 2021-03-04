package com.appointment.booking.service;

import com.appointment.booking.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    void saveAppointment(Appointment appointment);
    Optional<Appointment> getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsByClientId(Long id);
    List<Appointment> getAppointmentsByEmployeeId(Long id);
}
