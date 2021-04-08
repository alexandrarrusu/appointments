package com.appointment.booking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private Long client_id;
    private Long employee_id;
    private Long offer_id;
    private Long company_id;
    @CreationTimestamp
    private Timestamp creationTime;
    @UpdateTimestamp
    private Timestamp updateTime;

    public Appointment() {}

    private Appointment(AppointmentBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.time = builder.time;
        this.client_id = builder.client_id;
        this.employee_id = builder.employee_id;
        this.offer_id = builder.offer_id;
        this.company_id = builder.company_id;
        this.creationTime = builder.creationTime;
        this.updateTime = builder.updateTime;
    }

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

    public Long getOffer_id() {
        return offer_id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }


    public static class AppointmentBuilder {
        private Long id;
        private LocalDate date;
        private LocalTime time;
        private Long client_id;
        private Long employee_id;
        private Long offer_id;
        private Long company_id;
        private Timestamp creationTime;
        private Timestamp updateTime;

        public AppointmentBuilder() {}

        public AppointmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentBuilder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public AppointmentBuilder client_id(Long client_id) {
            this.client_id = client_id;
            return this;
        }

        public AppointmentBuilder employee_id(Long employee_id) {
            this.employee_id = employee_id;
            return this;
        }

        public AppointmentBuilder offer_id(Long offer_id) {
            this.offer_id = offer_id;
            return this;
        }

        public AppointmentBuilder company_id(Long company_id) {
            this.company_id = company_id;
            return this;
        }

        public AppointmentBuilder creationTime(Timestamp creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public AppointmentBuilder updateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Appointment)) {
            return false;
        }

        Appointment a = (Appointment) o;

        return (a.getId().equals(((Appointment) o).id)) && (a.getOffer_id().equals(((Appointment) o).offer_id))
                && (a.getClient_id().equals(((Appointment) o).client_id)) && (a.getEmployee_id().equals(((Appointment) o).employee_id))
                && (a.getCompany_id().equals(((Appointment) o).company_id)) && (a.getDate().equals(((Appointment) o).date))
                && (a.getTime().equals(((Appointment) o).time)) && (a.getCreationTime()).equals(((Appointment) o).creationTime)
                && (a.getUpdateTime().equals(((Appointment) o).updateTime));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Appointment.class);
    }
}
