package com.appointment.booking.service.impl;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.repository.AppointmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private Appointment appointment;
    private final List<Appointment> list = new ArrayList<>();

    @Before
    public void setup() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        appointment = new Appointment.AppointmentBuilder().id(1L).date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0)).client_id(2L).employee_id(3L).offer_id(1L).company_id(1L)
                .creationTime(timeStampDate).updateTime(timeStampDate).build();
        list.add(appointment);
    }

    @Test
    public void saveAppointment() {
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Appointment a = appointmentService.saveAppointment(appointment);
        assertEquals(appointment.getId(), a.getId());
        assertEquals(appointment.getClient_id(), a.getClient_id());
        assertEquals(appointment.getEmployee_id(), a.getEmployee_id());
        assertEquals(appointment.getOffer_id(), a.getOffer_id());
        assertEquals(appointment.getCompany_id(), a.getCompany_id());
        assertEquals(appointment.getDate(), a.getDate());
        assertEquals(appointment.getTime(), a.getTime());
        assertEquals(appointment.getCreationTime(), a.getCreationTime());
        assertEquals(appointment.getUpdateTime(), a.getUpdateTime());
    }

    @Test
    public void getAppointmentById() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));
        Optional<Appointment> a = appointmentService.getAppointmentById(appointment.getId());
        Appointment app = a.get();
        assertEquals(appointment.getId(), app.getId());
        assertEquals(appointment.getClient_id(), app.getClient_id());
    }

    @Test
    public void getAllAppointments() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        Appointment secondAppointment = new Appointment.AppointmentBuilder().id(2L)
                .date(LocalDate.of(2021, 3, 15)).time(LocalTime.of(15, 0))
                .client_id(3L).employee_id(1L).offer_id(3L).company_id(2L).creationTime(timeStampDate)
                .updateTime(timeStampDate).build();
        list.add(secondAppointment);
        when(appointmentRepository.findAll()).thenReturn(list);
        List<Appointment> savedAppointments = appointmentService.getAllAppointments();
        assertEquals(list.size(), savedAppointments.size());
    }

    @Test
    public void getAppointmentByClientId() {
        when(appointmentRepository.getAppointmentsByClientId(2L)).thenReturn(list);
        List<Appointment> clientAppointments = appointmentService.getAppointmentsByClientId(2L);
        assertEquals(1, clientAppointments.size());
    }

    @Test
    public void getAppointmentByEmployeeId() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        Appointment secondAppointment = new Appointment.AppointmentBuilder().id(2L)
                .date(LocalDate.of(2021, 3, 15)).time(LocalTime.of(15, 0))
                .client_id(3L).employee_id(3L).offer_id(3L).company_id(2L).creationTime(timeStampDate)
                .updateTime(timeStampDate).build();
        list.add(secondAppointment);
        when(appointmentRepository.getAppointmentsByEmployeeId(3L)).thenReturn(list);
        List<Appointment> employeeAppointments = appointmentService.getAppointmentsByEmployeeId(3L);
        assertEquals(list.size(), employeeAppointments.size());
    }
}