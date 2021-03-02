package com.appointment.booking.email;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class Email {

    private final String mailTo;
    private final LocalDate date;
    private final LocalTime time;
    private final String employeeName;
    private final String offer;
    private final String place;
    private final Double price;
    private final Timestamp creationDate;

    private Email(EmailBuilder builder) {
        this.mailTo = builder.mailTo;
        this.date = builder.date;
        this.time = builder.time;
        this.employeeName = builder.employeeName;
        this.offer = builder.offer;
        this.place = builder.place;
        this.price = builder.price;
        this.creationDate = builder.creationDate;
    }

    public String getMailTo() {
        return mailTo;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getOffer() {
        return offer;
    }

    public String getPlace() {
        return place;
    }

    public Double getPrice() {
        return price;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public static class EmailBuilder {
        private final String mailTo;
        private final String place;
        private final String employeeName;
        private final LocalDate date;
        private final LocalTime time;
        private String offer;
        private Double price;
        private Timestamp creationDate;

        public EmailBuilder(String mailTo, String place, String employeeName, LocalDate date, LocalTime time) {
            this.mailTo = mailTo;
            this.place = place;
            this.employeeName = employeeName;
            this.date = date;
            this.time = time;
        }

        public EmailBuilder offer(String offer) {
            this.offer = offer;
            return this;
        }

        public EmailBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public EmailBuilder creationDate(Timestamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Email build() {
            Email email =  new Email(this);
            return email;
        }
    }
}
