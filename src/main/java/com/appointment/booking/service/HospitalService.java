package com.appointment.booking.service;

import com.appointment.booking.entity.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService {

    void saveHospital(Hospital hospital);
    Optional<Hospital> getHospitalById(Long id);
    List<Hospital> getAllHospitals();
}
