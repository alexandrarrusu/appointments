package com.appointment.booking.email;

import java.time.LocalDate;
import java.time.LocalTime;

public class Email {

    private String mailTo;
    private LocalDate date;
    private LocalTime time;
    private String employeeName;

    public Email(String mailTo, LocalDate date, LocalTime time, String employeeName) {
        this.mailTo = mailTo;
        this.date = date;
        this.time = time;
        this.employeeName = employeeName;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
