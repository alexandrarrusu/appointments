package com.appointment.booking.controller;

import com.appointment.booking.email.EmailService;
import com.appointment.booking.entity.Appointment;
import com.appointment.booking.exception.NotFoundException;
import com.appointment.booking.response.Response;
import com.appointment.booking.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@RestController
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;
    private final EmailService emailService;

    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService,
                                 EmailService emailService) {
        this.appointmentService = appointmentService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public ResponseEntity<Response<Appointment>> saveAppointment(@RequestBody Appointment appointment)
            throws MessagingException {
        Appointment saveAppointment = appointmentService.saveAppointment(appointment);
        List<Appointment> list = new ArrayList<>();
        list.add(saveAppointment);
        emailService.sendEmailToClient(appointment);
        return new ResponseEntity<>(new Response<>("Appointment added", "201", list),
                HttpStatus.CREATED);
    }

    @RequestMapping(value="/appointment/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Appointment>> getAppointmentById(@PathVariable Long id) {
        List<Appointment> list = new ArrayList<>();
        Optional<Appointment> a = appointmentService.getAppointmentById(id);
        if(a.isPresent()) {
            list.add(a.get());
        } else {
            throw new NotFoundException("Appointment with id = " + id + " not found");
        }
        return new ResponseEntity<>(new Response<>("Appointment with id = " + id, "200", list),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/appointment/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Response<Appointment>> updateAppointment(@PathVariable Long id,
                                                                   @RequestBody Appointment updatedAppointment)
            throws MessagingException {
        Optional<Appointment> initialAppointment = appointmentService.getAppointmentById(id);
        Appointment appointment = null;
        if(initialAppointment.isPresent()) {
            Long appointmentId = initialAppointment.get().getId();
            Timestamp creationTime = initialAppointment.get().getCreationTime();
            appointment = new Appointment.AppointmentBuilder()
                    .id(appointmentId)
                    .date(updatedAppointment.getDate())
                    .time(updatedAppointment.getTime())
                    .offer_id(updatedAppointment.getOffer_id())
                    .client_id(updatedAppointment.getClient_id())
                    .employee_id(updatedAppointment.getEmployee_id())
                    .company_id(updatedAppointment.getCompany_id())
                    .creationTime(creationTime)
                    .build();
        }
        appointmentService.saveAppointment(appointment);
        emailService.sendEmailToClient(appointment);
        return new ResponseEntity<>(new Response<>("Appointment updated", "201", emptyList()),
                HttpStatus.CREATED);
    }

    @RequestMapping(value="/appointment", method = RequestMethod.GET)
    public ResponseEntity<Response<Appointment>> getAllAppointments(
            @RequestParam(value = "clientId", required = false) Long clientId,
            @RequestParam(value = "employeeId", required = false) Long employeeId) {
        if(clientId != null) {
            return new ResponseEntity<>(new Response<>("Appointments found for client with id = " + clientId,
                "200", appointmentService.getAppointmentsByClientId(clientId)), HttpStatus.OK);
        }
        if(employeeId != null) {
            return new ResponseEntity<>(new Response<>("Appointments found for employee with id = " +
                    employeeId, "200", appointmentService.getAppointmentsByEmployeeId(employeeId)),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response<>("Appointments found", "200",
                appointmentService.getAllAppointments()), HttpStatus.OK);
    }
}
