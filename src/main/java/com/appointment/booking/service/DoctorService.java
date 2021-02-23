package com.appointment.booking.service;

import com.appointment.booking.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    void saveDoctor(Doctor doctor);
    Optional<Doctor> getDoctorById(Long id);
    List<Doctor> getAllDoctors();
}
