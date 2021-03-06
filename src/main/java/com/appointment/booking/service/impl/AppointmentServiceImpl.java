package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.repository.AppointmentRepository;
import com.appointment.booking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByClientId(Long id) {
        return appointmentRepository.getAppointmentsByClientId(id);
    }

    @Override
    public List<Appointment> getAppointmentsByEmployeeId(Long id) {
        return appointmentRepository.getAppointmentsByEmployeeId(id);
    }
}
