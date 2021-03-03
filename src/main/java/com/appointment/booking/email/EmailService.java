package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.service.impl.ClientServiceImpl;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import com.appointment.booking.service.impl.OfferServiceImpl;
import com.appointment.booking.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmailService {

    @Value("${mail.from}")
    private String mailFrom;

    private final EmailConfig emailConfig;
    private final ClientServiceImpl clientService;
    private final EmployeeServiceImpl employeeService;
    private final OfferServiceImpl offerService;
    private final CompanyServiceImpl companyService;

    @Autowired
    public EmailService(EmailConfig emailConfig, ClientServiceImpl clientService, EmployeeServiceImpl employeeService,
                        OfferServiceImpl offerService, CompanyServiceImpl companyService) {
        this.emailConfig = emailConfig;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.offerService = offerService;
        this.companyService = companyService;
    }

    public void sendEmailToClient(Appointment appointment) throws MessagingException {
        // Send mail
        getMailSender().send(getMailMessage(getEmailBodyDetails(appointment)));
    }

    private JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());
        return mailSender;
    }

    private MimeMessage getMailMessage(Email body) throws MessagingException {
        String mailBody = "<p>Service: " + body.getOffer() + "</p><br/>" +
                "<p>Price: " + body.getPrice() + "<p><br/>" +
                "<p>Location: " + body.getCompany() + "<p><br/>" +
                "<p>Date: " + body.getDate() + "</p><br/>" +
                "<p>Time: " + body.getTime() + "</p><br/>" +
                "<p>Employee: " + body.getEmployeeName() + "</p><br/>" +
                "<p>Booking date: " + body.getCreationDate() + "</p>";
        MimeMessage mailMessage = getMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setFrom(mailFrom);
        helper.setTo(body.getMailTo());
        helper.setSubject("Your appointment details");
        helper.setText(mailBody, true);
        return mailMessage;
    }

    private Email getEmailBodyDetails(Appointment appointment) {
        LocalDate date = appointment.getDate();
        LocalTime time = appointment.getTime();
        Timestamp creationDate = appointment.getCreationTime();
        String mailTo = clientService.getClientEmailById(appointment.getClient_id());
        String employeeName = employeeService.getEmployeeNameById(appointment.getEmployee_id())
                .replace(",", " ");
        String offerName = offerService.getOfferNameById(appointment.getOffer_id());
        Double offerPrice = offerService.getOfferPriceById(appointment.getOffer_id());
        String companyName = companyService.getCompanyNameById(appointment.getCompany_id());

        return new Email.EmailBuilder(mailTo, companyName, employeeName, date, time)
                .offer(offerName)
                .price(offerPrice)
                .creationDate(creationDate)
                .build();
    }
}
