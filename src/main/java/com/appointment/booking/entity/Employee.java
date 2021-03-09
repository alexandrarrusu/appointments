package com.appointment.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Long company_id;

    public Employee() {}

    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.company_id = builder.company_id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public static class EmployeeBuilder {
        private Long id;
        private String firstname;
        private String lastname;
        private Long company_id;

        public EmployeeBuilder() {}

        public EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public EmployeeBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public EmployeeBuilder company_id(Long company_id) {
            this.company_id = company_id;
            return this;
        }

        public Employee build() {return new Employee(this);}
    }
}
