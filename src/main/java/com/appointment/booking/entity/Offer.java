package com.appointment.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    public Offer() {}

    private Offer(OfferBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class OfferBuilder {
        private Long id;
        private String name;
        private BigDecimal price;

        public OfferBuilder() {}

        public OfferBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OfferBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OfferBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Offer build() {return new Offer(this);}
    }

}
