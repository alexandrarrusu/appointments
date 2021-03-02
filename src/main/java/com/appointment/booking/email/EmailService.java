package com.appointment.booking.email;

import com.appointment.booking.entity.Appointment;
import com.appointment.booking.service.impl.ClientServiceImpl;
import com.appointment.booking.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmailService {

    @Value("${mail.from}")
    private String mailFrom;

    private final EmailConfig emailConfig;
    private final ClientServiceImpl clientService;
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmailService(EmailConfig emailConfig, ClientServiceImpl clientService,
                        EmployeeServiceImpl employeeService) {
        this.emailConfig = emailConfig;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    public void sendEmailToClient(Appointment appointment) throws MessagingException {
        // Send mail
        String mailTo = clientService.getEmailById(appointment.getClient_id());
        String employeeName = employeeService.getNameById(appointment.getEmployee_id()).
                replace(",", " ");
        LocalDate date = appointment.getDate();
        LocalTime time = appointment.getTime();
        Email body = new Email(mailTo, date, time, employeeName);
        getMailSender().send(getMailMessage(mailTo, body));
    }

    private JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());
        return mailSender;
    }

    private MimeMessage getMailMessage(String mailTo, Email body) throws MessagingException {
        String mailBody = "<p>Date: " + body.getDate() + "</p>" + "<br/><p>Time: " + body.getTime() + "</p></br>" +
                "<p>Employee: " + body.getEmployeeName() + "</p>";
        MimeMessage mailMessage = getMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setFrom(mailFrom);
        helper.setTo(mailTo);
        helper.setSubject("Your appointment details");
        helper.setText(mailBody, true);
        return mailMessage;
    }
}
