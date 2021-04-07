package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.service.impl.ClientServiceImpl;
import com.appointment.booking.service.impl.CompanyServiceImpl;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import com.appointment.booking.service.impl.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class EmailDetails {

    private final ClientServiceImpl clientService;
    private final EmployeeServiceImpl employeeService;
    private final OfferServiceImpl offerService;
    private final CompanyServiceImpl companyService;

    @Autowired
    public EmailDetails(ClientServiceImpl clientService, EmployeeServiceImpl employeeService,
                               OfferServiceImpl offerService, CompanyServiceImpl companyService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.offerService = offerService;
        this.companyService = companyService;
    }

    public Email getEmailBodyDetails(Appointment appointment) {
        LocalDate date = appointment.getDate();
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        LocalTime time = appointment.getTime();
        Timestamp creationTime = appointment.getCreationTime();
        Timestamp updateTime = appointment.getUpdateTime();
        String mailTo = clientService.getClientEmailById(appointment.getClient_id());
        String employeeName = employeeService.getEmployeeNameById(appointment.getEmployee_id());
        String offerName = offerService.getOfferNameById(appointment.getOffer_id());
        BigDecimal offerPrice = offerService.getOfferPriceById(appointment.getOffer_id());
        String companyName = companyService.getCompanyNameById(appointment.getCompany_id());

        return new Email.EmailBuilder(mailTo, companyName, employeeName, formattedDate, time)
                .offer(offerName)
                .price(offerPrice)
                .creationTime(creationTime)
                .updateTime(updateTime)
                .build();
    }
}
