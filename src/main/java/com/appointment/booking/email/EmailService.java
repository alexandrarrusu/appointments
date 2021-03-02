package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.entity.Client;
import com.appointment.booking.entity.Employee;
import com.appointment.booking.entity.Offer;
import com.appointment.booking.service.impl.ClientServiceImpl;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import com.appointment.booking.service.impl.OfferServiceImpl;
import com.appointment.booking.service.impl.PlaceServiceImpl;
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
import java.util.Optional;

@Service
public class EmailService {

    @Value("${mail.from}")
    private String mailFrom;

    private final EmailConfig emailConfig;
    private final ClientServiceImpl clientService;
    private final EmployeeServiceImpl employeeService;
    private final OfferServiceImpl offerService;
    private final PlaceServiceImpl placeService;

    @Autowired
    public EmailService(EmailConfig emailConfig, ClientServiceImpl clientService, EmployeeServiceImpl employeeService,
                        OfferServiceImpl offerService, PlaceServiceImpl placeService) {
        this.emailConfig = emailConfig;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.offerService = offerService;
        this.placeService = placeService;
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
                "<p>Location: " + body.getPlace() + "<p><br/>" +
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
        Optional<Client> client = clientService.getClientById(appointment.getClient_id());
        Optional<Employee> employee = employeeService.getEmployeeById(appointment.getEmployee_id());
        Optional<Offer> offer = offerService.getOfferById(appointment.getOffer_id());

        String mailTo = "";
        if(client.isPresent()) {
            mailTo = client.get().getEmail();
        }

        String employeeFirstName = "";
        String employeeLastName = "";
        if(employee.isPresent()) {
            employeeFirstName = employee.get().getFirstname();
            employeeLastName = employee.get().getLastname();
        }
        String employeeName = employeeFirstName + " " + employeeLastName;

       String offerName = "";
       Double offerPrice = 0.0;
       if(offer.isPresent()) {
           offerName = offer.get().getName();
           offerPrice = offer.get().getPrice();
       }

       String placeName = "";
       if(placeService.getPlaceById(appointment.getPlace_id()).isPresent()) {
           placeName = placeService.getPlaceById(appointment.getPlace_id()).get().getName();
       }

        return new Email.EmailBuilder(mailTo, placeName, employeeName, date, time)
                .offer(offerName)
                .price(offerPrice)
                .creationDate(creationDate)
                .build();
    }
}
