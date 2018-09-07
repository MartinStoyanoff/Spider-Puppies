package com.spidermanteam.spiderpuppies.models.reporting;

import com.spidermanteam.spiderpuppies.models.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceView {

  private int id;

  private String subscriberName;

  private String subscriberPhone;

  private String telecomServiceType;

  private String telecomServiceSubscriptionPlan;

  private String status;

  private BigDecimal price;

  private String currency;

  private LocalDate startDate;

  private LocalDate endDate;

  private LocalDate paymentDate;

  public InvoiceView(Invoice invoice) {
    this.id = invoice.getId();
    this.subscriberName = invoice.getSubscriber().getFirstName() + " " + invoice.getSubscriber().getLastName();
    this.subscriberPhone = invoice.getSubscriber().getPhone();
    this.telecomServiceType = invoice.getTelecomService().getType();
    this.telecomServiceSubscriptionPlan = invoice.getTelecomService().getSubscriptionPlan();
    this.status = invoice.getStatus();
    this.price = invoice.getPrice();
    this.currency = invoice.getCurrency();
    this.startDate = invoice.getStartDate();
    this.endDate = invoice.getEndDate();
    this.paymentDate = invoice.getPaymentDate();
  }

  public InvoiceView() {
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

  public String getSubscriberPhone() {
    return subscriberPhone;
  }

  public void setSubscriberPhone(String subscriberPhone) {
    this.subscriberPhone = subscriberPhone;
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


