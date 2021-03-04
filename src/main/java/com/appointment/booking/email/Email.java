package com.appointment.booking.email;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;

public class Email {

    private final String mailTo;
    private final String date;
    private final LocalTime time;
    private final String employeeName;
    private final String offer;
    private final String company;
    private final BigDecimal price;
    @JsonFormat(pattern="HH:mm:ss")
    private final Timestamp creationDate;

    private Email(EmailBuilder builder) {
        this.mailTo = builder.mailTo;
        this.date = builder.date;
        this.time = builder.time;
        this.employeeName = builder.employeeName;
        this.offer = builder.offer;
        this.company = builder.company;
        this.price = builder.price;
        this.creationDate = builder.creationDate;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getDate() {
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

    public String getCompany() {
        return company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public static class EmailBuilder {
        private final String mailTo;
        private final String company;
        private final String employeeName;
        private final String date;
        private final LocalTime time;
        private String offer;
        private BigDecimal price;
        private Timestamp creationDate;

        public EmailBuilder(String mailTo, String company, String employeeName, String date, LocalTime time) {
            this.mailTo = mailTo;
            this.company = company;
            this.employeeName = employeeName;
            this.date = date;
            this.time = time;
        }

        public EmailBuilder offer(String offer) {
            this.offer = offer;
            return this;
        }

        public EmailBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public EmailBuilder creationDate(Timestamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
