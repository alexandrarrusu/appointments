package com.appointment.booking.validator;

import com.appointment.booking.entity.Patient;
import com.appointment.booking.exception.BadRequestException;
import com.appointment.booking.repository.PatientRepository;

public class PatientValidator {

    public void validatePatient(Patient patient, PatientRepository patientRepository) {
        String email = patient.getEmail();
        if(!patientRepository.checkEmailIfExists(email).getEmail().isEmpty()) {
            throw new BadRequestException("Email already exists");
        }
    }

}
