package com.appointment.booking.validator;

import com.appointment.booking.entity.Patient;
import com.appointment.booking.exception.BadRequestException;
import com.appointment.booking.repository.PatientRepository;

import static java.util.Objects.isNull;

public class PatientValidator {

    public void validatePatient(Patient patient, PatientRepository patientRepository) {
        String email = patient.getEmail();
        String phone = patient.getPhone();
        validateEmail(email, patientRepository);
        validatePhone(phone);
    }

    private void validateEmail(String email, PatientRepository patientRepository) {
        if(email.length() == 0) {
            throw new BadRequestException("Email cannot be empty");
        }

        if(!isNull(patientRepository.checkEmailIfExists(email))) {
            throw new BadRequestException("Email already exists");
        }
    }

    private void validatePhone(String phone) {
        if(phone.length() == 0) {
            throw new BadRequestException("Phone cannot be null");
        }
    }

}
