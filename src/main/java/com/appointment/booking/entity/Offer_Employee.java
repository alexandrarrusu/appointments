package com.appointment.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer_Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employee_id;
    private Long offer_id;

    public Offer_Employee() {}

    private Offer_Employee(Offer_EmployeeBuilder builder) {
        this.id = builder.id;
        this.employee_id = builder.employee_id;
        this.offer_id = builder.offer_id;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public Long getOffer_id() {
        return offer_id;
    }

    public static class Offer_EmployeeBuilder {
        private Long id;
        private Long employee_id;
        private Long offer_id;

        public Offer_EmployeeBuilder() {}

        public Offer_EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Offer_EmployeeBuilder employee_id(Long employee_id) {
            this.employee_id = employee_id;
            return this;
        }

        public Offer_EmployeeBuilder offer_id(Long offer_id) {
            this.offer_id = offer_id;
            return this;
        }

        public Offer_Employee build() {
            return new Offer_Employee(this);
        }
    }
}
