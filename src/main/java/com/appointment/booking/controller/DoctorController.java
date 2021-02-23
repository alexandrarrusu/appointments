package com.appointment.booking.controller;

import com.appointment.booking.controller.response.Response;
import com.appointment.booking.entity.Doctor;
import com.appointment.booking.entity.Patient;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class DoctorController {

    private final DoctorServiceImpl doctorService;

    @Autowired
    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public ResponseEntity<Response<Doctor>> saveDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return ResponseEntity.ok().body(new Response<>("Doctor was added", 201, emptyList()));
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public ResponseEntity<Response<Doctor>> getAllDoctors() {
        return ResponseEntity.ok().body(new Response<>("Doctors found", 200,
                doctorService.getAllDoctors()));
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Doctor>> getDoctorById(@PathVariable Long id) {
        List<Doctor> list = new ArrayList<>();
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        if(doctor.isPresent()) {
            list.add(doctor.get());
        } else {
            throw new NotFoundException("Doctor not found");
        }
        return ResponseEntity.ok().body(new Response<>("Doctor with id = " + id, 200, list));
    }
}
