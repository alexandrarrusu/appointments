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
import java.util.Date;
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

    @Before
    public void setup() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        appointment = new Appointment.AppointmentBuilder()
                .id(1L)
                .date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0))
                .client_id(2L)
                .employee_id(3L)
                .offer_id(1L)
                .company_id(1L)
                .creationTime(timeStampDate)
                .updateTime(timeStampDate)
                .build();
    }

    @Test
    public void saveAppointment() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Appointment a = appointmentService.saveAppointment(appointment);
        assertEquals(Long.valueOf(1L), a.getId());
        assertEquals(Long.valueOf(2L), a.getClient_id());
        assertEquals(Long.valueOf(3L), a.getEmployee_id());
        assertEquals(Long.valueOf(1L), a.getOffer_id());
        assertEquals(Long.valueOf(1L), a.getCompany_id());
        assertEquals(LocalDate.of(2021, 3, 10), a.getDate());
        assertEquals(LocalTime.of(10, 0), a.getTime());
        assertEquals(timeStampDate, a.getCreationTime());
        assertEquals(timeStampDate, a.getUpdateTime());
    }

    @Test
    public void getAppointmentById() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        Optional<Appointment> a = appointmentService.getAppointmentById(1L);
        Appointment ap = a.get();
        assertEquals(Long.valueOf(1L),ap.getId());
        assertEquals(Long.valueOf(2L), ap.getClient_id());
    }

    @Test
    public void getAllAppointments() {

    }

    @Test
    public void getAppointmentByClientId() {

    }

    @Test
    public void getAppointmentByEmployeeId() {

    }
}