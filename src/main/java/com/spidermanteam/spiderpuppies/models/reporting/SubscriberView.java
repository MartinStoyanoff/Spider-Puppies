package com.spidermanteam.spiderpuppies.models.reporting;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriberView {

  private int id;
  private String phone;
  private String firstName;
  private String lastName;
  private String personalIdentificationNumber;
  private String address;
  private List<InvoiceView> invoices;
  private List<TelecomService> telecomServices;
  private LocalDate firstServiceActivationDate;
  private LocalDate billingDate;
  private BigDecimal allTimeTurnover;

  public SubscriberView() {
  }

  public SubscriberView(Subscriber subscriber) {
    this.id = subscriber.getId();
    this.phone = subscriber.getPhone();
    this.firstName = subscriber.getFirstName();
    this.lastName = subscriber.getLastName();
    this.personalIdentificationNumber = subscriber.getPersonalIdentificationNumber();
    this.address = subscriber.getAddress();
    List<Invoice> subscriberInvoices = subscriber.getInvoices();
    this.invoices = new ArrayList<>();
    for (Invoice inv : subscriberInvoices
    ) {
      InvoiceView invReport = new InvoiceView(inv);
      invoices.add(invReport);
    }
    this.telecomServices = subscriber.getTelecomServices();
    this.firstServiceActivationDate = subscriber.getFirstServiceActivationDate();
    this.billingDate = subscriber.getBillingDate();
    this.allTimeTurnover = subscriber.getAllTimeTurnover();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPersonalIdentificationNumber() {
    return personalIdentificationNumber;
  }

  public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
    this.personalIdentificationNumber = personalIdentificationNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<InvoiceView> getInvoices() {
    return invoices;
  }

  public void setInvoices(List<InvoiceView> invoices) {
    this.invoices = invoices;
  }

  public List<TelecomService> getTelecomServices() {
    return telecomServices;
  }

  public void setTelecomServices(List<TelecomService> telecomServices) {
    this.telecomServices = telecomServices;
  }

  public LocalDate getFirstServiceActivationDate() {
    return firstServiceActivationDate;
  }

  public void setFirstServiceActivationDate(LocalDate firstServiceActivationDate) {
    this.firstServiceActivationDate = firstServiceActivationDate;
  }

  public LocalDate getBillingDate() {
    return billingDate;
  }

  public void setBillingDate(LocalDate billingDate) {
    this.billingDate = billingDate;
  }

  public BigDecimal getAllTimeTurnover() {
    return allTimeTurnover;
  }

  public void setAllTimeTurnover(BigDecimal allTimeTurnover) {
    this.allTimeTurnover = allTimeTurnover;
  }
}
