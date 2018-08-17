package com.spidermanteam.spiderpuppies.models;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "telecom_services")
public class TelecomService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "subscripion_plan")
    private String subscriptionPlan;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "paid")
    private int paid;

    public TelecomService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
