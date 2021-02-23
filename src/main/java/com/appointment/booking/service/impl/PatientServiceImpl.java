package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Patient;
import com.appointment.booking.repository.PatientRepository;
import com.appointment.booking.service.PatientService;
import com.appointment.booking.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private PatientValidator patientValidator = new PatientValidator();

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void savePatient(Patient patient) {
        patientValidator.validatePatient(patient, patientRepository);
        patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
