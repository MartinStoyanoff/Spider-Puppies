package com.spidermanteam.spiderpuppies.models.reporting;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceReport {

    private int id;

    private String subscriberName;

    private String getSubscriberPhone;

    private String telecomServiceType;

    private String telecomServiceSubscriptionPlan;

    private String status;

    private BigDecimal price;

    private String currency;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate paymentDate;

    public InvoiceReport(int id, String subscriberName, String getSubscriberPhone, String telecomServiceType, String telecomServiceSubscriptionPlan, String status, BigDecimal price, String currency, LocalDate startDate, LocalDate endDate, LocalDate paymentDate) {
        this.id = id;
        this.subscriberName = subscriberName;
        this.getSubscriberPhone = getSubscriberPhone;
        this.telecomServiceType = telecomServiceType;
        this.telecomServiceSubscriptionPlan = telecomServiceSubscriptionPlan;
        this.status = status;
        this.price = price;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentDate = paymentDate;
    }

    public InvoiceReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getGetSubscriberPhone() {
        return getSubscriberPhone;
    }

    public void setGetSubscriberPhone(String getSubscriberPhone) {
        this.getSubscriberPhone = getSubscriberPhone;
    }

    public String getTelecomServiceType() {
        return telecomServiceType;
    }

    public void setTelecomServiceType(String telecomServiceType) {
        this.telecomServiceType = telecomServiceType;
    }

    public String getTelecomServiceSubscriptionPlan() {
        return telecomServiceSubscriptionPlan;
    }

    public void setTelecomServiceSubscriptionPlan(String telecomServiceSubscriptionPlan) {
        this.telecomServiceSubscriptionPlan = telecomServiceSubscriptionPlan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}


