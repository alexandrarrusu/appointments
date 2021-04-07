package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${mail.from}")
    private String mailFrom;

    private final JavaMailSender mailSender;
    private final EmailDetails emailDetailsService;

    @Autowired
    public EmailService(JavaMailSender mailSender, EmailDetails emailDetailsService) {
        this.mailSender = mailSender;
        this.emailDetailsService = emailDetailsService;
    }

    public void sendEmailToClient(Appointment appointment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailFrom);
        Email email = emailDetailsService.getEmailBodyDetails(appointment);
        String mailBody = "<p>Service: " + email.getOffer() + "</p><br/>" +
                "<p>Price: " + email.getPrice() + "<p><br/>" +
                "<p>Location: " + email.getCompany() + "<p><br/>" +
                "<p>Date: " + email.getDate() + "</p><br/>" +
                "<p>Time: " + email.getTime() + "</p><br/>" +
                "<p>Employee: " + email.getEmployeeName() + "</p><br/>" +
                "<p>Booking date: " + email.getCreationDate() + "</p>";
        helper.setTo(email.getMailTo());
        helper.setSubject("Your appointment details");
        helper.setText(mailBody, true);

        mailSender.send(message);
    }
}
