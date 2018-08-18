package com.spidermanteam.spiderpuppies.models;

import jdk.internal.instrumentation.TypeMapping;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "telecom_services")
public class TelecomService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "subscription_plan")
    private String subscriptionPlan;

    @Column(name = "price", columnDefinition = "big_decimal")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "services_subscribers",
            joinColumns =  @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
    private List<Subscriber> subscribers;


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

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

}
