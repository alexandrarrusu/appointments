package com.appointment.booking.controller;

import com.appointment.booking.controller.response.Response;
import com.appointment.booking.entity.Patient;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class PatientController {

    private final PatientServiceImpl patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public ResponseEntity<Response<Patient>> savePatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        return ResponseEntity.ok().body(new Response<>("Patient was added", 201, emptyList()));
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public ResponseEntity<Response<Patient>> getAllPatients() {
        return ResponseEntity.ok().body(new Response<>("Patients found", 200,
                patientService.getAllPatients()));
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Patient>> getPatientById(@PathVariable Long id) {
        List<Patient> list = new ArrayList<>();
        Optional<Patient> patient = patientService.getPatientById(id);
        if(patient.isPresent()) {
            list.add(patient.get());
        } else {
            throw new NotFoundException("Patient not found");
        }
        return ResponseEntity.ok().body(new Response<>("Patient with id = " + id, 200, list));
    }
}
