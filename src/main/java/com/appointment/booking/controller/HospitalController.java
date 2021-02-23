package com.appointment.booking.controller;

import com.appointment.booking.controller.response.Response;
import com.appointment.booking.entity.Hospital;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.service.impl.HospitalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class HospitalController {

    private final HospitalServiceImpl hospitalService;

    @Autowired
    public HospitalController(HospitalServiceImpl hospitalService) {
        this.hospitalService = hospitalService;
    }

    @RequestMapping(value = "/hospital", method = RequestMethod.POST)
    public ResponseEntity<Response<Hospital>> saveHospital(@RequestBody Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return ResponseEntity.ok().body(new Response<>("Hospital was added", 201, emptyList()));
    }

    @RequestMapping(value = "/hospital", method = RequestMethod.GET)
    public ResponseEntity<Response<Hospital>> getAllHospitals() {
        return ResponseEntity.ok().body(new Response<>("Hospitals found", 200,
                hospitalService.getAllHospitals()));
    }

    @RequestMapping(value = "/hospital/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Hospital>> getHospitalById(@PathVariable Long id) {
        List<Hospital> list = new ArrayList<>();
        Optional<Hospital> hospital = hospitalService.getHospitalById(id);
        if(hospital.isPresent()) {
            list.add(hospital.get());
        } else {
            throw new NotFoundException("Hospital not found");
        }
        return ResponseEntity.ok().body(new Response<>("Hospital with id = " + id, 200, list));
    }
}
