package com.appointment.booking.repository;

import com.appointment.booking.entity.Appointment;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class AppointmentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Before
    public void setup() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        Appointment appointment1 = new Appointment.AppointmentBuilder().date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0)).client_id(1L).employee_id(1L).offer_id(1L).company_id(1L)
                .creationTime(timeStampDate).updateTime(timeStampDate).build();
        Appointment appointment2 = new Appointment.AppointmentBuilder().date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0)).client_id(1L).employee_id(2L).offer_id(3L).company_id(1L)
                .creationTime(timeStampDate).updateTime(timeStampDate).build();
        testEntityManager.persist(appointment1);
        testEntityManager.persist(appointment2);
        testEntityManager.flush();
    }

    @Test
    public void getAppointmentsByClientId() {
        assertEquals(2, appointmentRepository.getAppointmentsByClientId(1L).size());
    }

    @Test
    public void getAppointmentsByEmployeeId() {
        assertEquals(1, appointmentRepository.getAppointmentsByEmployeeId(1L).size());
    }

}