package com.appointment.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    public Client() {}

    private Client(ClientBuilder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.phone = builder.phone;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static class ClientBuilder {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private String phone;

        public ClientBuilder() {}

        public ClientBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public ClientBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
