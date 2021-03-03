package com.appointment.booking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    private Long client_id;
    private Long employee_id;
    private Long offer_id;
    private Long company_id;
    @CreationTimestamp
    private Timestamp creationTime;

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Long getClient_id() {
        return client_id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public Long getOffer_id() {
        return offer_id;
    }

    public Long getCompany_id() {
        return company_id;
    }
}
