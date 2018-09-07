package com.spidermanteam.spiderpuppies.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "subscriber_id")
  @JsonIdentityReference(alwaysAsId = true)
  private Subscriber subscriber;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "telecom_service")
  private TelecomService telecomService;

  @Column(name = "status")
  private String status;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "currency")
  private String currency;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "payment_date")
  private LocalDate paymentDate;


  public Invoice() {
  }

  public Invoice(Subscriber subscriber, TelecomService telecomServices, BigDecimal price, String currency) {
    this.subscriber = subscriber;
    this.telecomService = telecomServices;
    this.currency = currency;
    this.status = "0";
    this.price = price;
    setStartDate();
    setEndDate();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Subscriber getSubscriber() {
    return subscriber;
  }

  public void setSubscriber(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

  public TelecomService getTelecomService() {
    return telecomService;
  }

  public void setTelecomService(TelecomService telecomServices) {
    this.telecomService = telecomServices;
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

  public void setStartDate() {
    this.startDate = subscriber.getBillingDate();
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate() {
    this.endDate = subscriber.getBillingDate().plusMonths(1);
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }


}
