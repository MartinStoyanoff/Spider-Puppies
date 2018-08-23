package com.spidermanteam.spiderpuppies.models.reporting;

import java.math.BigDecimal;

public class PaymentLine {
    private int subscriberId;
    private String subscriberPhone;
    private String serviceType;
    private String subscriptionPlan;
    private BigDecimal price;
    private String currency;

    public PaymentLine() {
    }

    public PaymentLine(int subscriberId, String subscriberPhone, String serviceType, String subscriptionPlan, BigDecimal price, String currency) {
        this.subscriberId = subscriberId;
        this.subscriberPhone = subscriberPhone;
        this.serviceType = serviceType;
        this.subscriptionPlan = subscriptionPlan;
        this.price = price;
        this.currency = currency;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriberPhone() {
        return subscriberPhone;
    }

    public void setSubscriberPhone(String subscriberPhone) {
        this.subscriberPhone = subscriberPhone;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
