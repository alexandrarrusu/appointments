package com.appointment.booking.service;

import com.appointment.booking.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    void savePatient(Patient patient);
    Optional<Patient> getPatientById(Long id);
    List<Patient> getAllPatients();
}
