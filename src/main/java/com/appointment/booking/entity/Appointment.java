package com.appointment.booking.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Time time;
    private Long client_id;
    private Long employee_id;
    private Long place_id;
    @CreationTimestamp
    private Timestamp creationDateTime;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public Long getClient_id() {
        return client_id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }
}
