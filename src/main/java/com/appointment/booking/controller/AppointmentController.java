package com.appointment.booking.controller;

import com.appointment.booking.controller.response.Response;
import com.appointment.booking.entity.Appointment;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.service.impl.AppointmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public ResponseEntity<Response<Appointment>> saveAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok().body(new Response<>("Appointment was added", 201, emptyList()));
    }

    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public ResponseEntity<Response<Appointment>> getAllAppointments() {
        return ResponseEntity.ok().body(new Response<>("Appointments found", 200,
                appointmentService.getAllAppointments()));
    }

    @RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Appointment>> getAppointmentById(@PathVariable Long id) {
        List<Appointment> list = new ArrayList<>();
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if(appointment.isPresent()) {
            list.add(appointment.get());
        } else {
            throw new NotFoundException("Appointment not found");
        }
        return ResponseEntity.ok().body(new Response<>("Appointment with id = " + id, 200, list));
    }

}
