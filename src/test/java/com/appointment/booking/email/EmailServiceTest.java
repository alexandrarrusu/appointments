package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest {

    @Mock
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage message;

    @Mock
    private EmailDetails emailDetails;

    @Before
    public void setup() {
        EmailService emailServiceSpy = new EmailService(javaMailSender, emailDetails);
        emailService = spy(emailServiceSpy);
        ReflectionTestUtils.setField(emailService, "mailFrom", "appointments@something.com");
    }

    @Test
    public void sendEmailToClient() throws MessagingException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("21/04/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        Appointment appointment = new Appointment.AppointmentBuilder().id(1L)
                .date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0)).client_id(2L)
                .employee_id(3L).offer_id(1L).company_id(1L)
                .creationTime(timeStampDate).updateTime(timeStampDate).build();

        when(javaMailSender.createMimeMessage()).thenReturn(message);

        Email email = new Email.EmailBuilder("java@mail.com", "Place1", "Anna",
                "21/04/2021", LocalTime.of(10, 0)).build();
        when(emailDetails.getEmailBodyDetails(appointment)).thenReturn(email);

        doNothing().when(javaMailSender).send(message);
        emailService.sendEmailToClient(appointment);

        verify(emailService, times(1)).sendEmailToClient(appointment);
        verify(javaMailSender, times(1)).send((message));

    }
}