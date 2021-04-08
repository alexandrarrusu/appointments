package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.service.impl.ClientServiceImpl;
import com.appointment.booking.service.impl.CompanyServiceImpl;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import com.appointment.booking.service.impl.OfferServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmailDetailsTest {

    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private EmployeeServiceImpl employeeService;
    @Mock
    private OfferServiceImpl offerService;
    @Mock
    private CompanyServiceImpl companyService;

    @InjectMocks
    private EmailDetails emailDetails;

    @Test
    public void getEmailBodyDetails() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("05/03/2021");
        Timestamp timeStampDate = new Timestamp(date.getTime());
        Appointment appointment = new Appointment.AppointmentBuilder().id(1L)
                .date(LocalDate.of(2021, 3, 10))
                .time(LocalTime.of(10, 0)).client_id(1L).employee_id(1L).offer_id(1L).company_id(1L)
                .creationTime(timeStampDate).updateTime(timeStampDate).build();

        when(clientService.getClientEmailById(1L)).thenReturn("java@mail.com");
        when(employeeService.getEmployeeNameById(1L)).thenReturn("Anna");
        when(offerService.getOfferNameById(1L)).thenReturn("Manicure");
        when(offerService.getOfferPriceById(1L)).thenReturn(new BigDecimal("55.00"));
        when(companyService.getCompanyNameById(1L)).thenReturn("Place1");

        Email email = emailDetails.getEmailBodyDetails(appointment);
        assertEquals("java@mail.com", email.getMailTo());
    }

}