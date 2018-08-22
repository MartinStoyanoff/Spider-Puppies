package com.spidermanteam.spiderpuppies.models.reporting;

import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.TelecomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SubscriberReport {

    private int id;
    private String phone;
    private String firstName;
    private String lastName;
    private String personalIdentificationNumber;
    private String address;
    private List<InvoiceReport> invoices;
//    private List<TelecomService> telecomServices;
    private LocalDate firstServiceActivationDate;
    private LocalDate billingDate;
    private Client client;
    private BigDecimal allTimeTurnover;

    public SubscriberReport() {
    }

    public SubscriberReport(int id, String phone, String firstName, String lastName, String personalIdentificationNumber, String address, List<InvoiceReport> invoices, LocalDate firstServiceActivationDate, LocalDate billingDate, Client client, BigDecimal allTimeTurnover) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.address = address;
        this.invoices = invoices;
//        this.telecomServices = telecomServices;
        this.firstServiceActivationDate = firstServiceActivationDate;
        this.billingDate = billingDate;
        this.client = client;
        this.allTimeTurnover = allTimeTurnover;
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

    public List<InvoiceReport> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceReport> invoices) {
        this.invoices = invoices;
    }

//    public List<TelecomService> getTelecomServices() {
//        return telecomServices;
//    }
//
//    public void setTelecomServices(List<TelecomService> telecomServices) {
//        this.telecomServices = telecomServices;
//    }
//
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getAllTimeTurnover() {
        return allTimeTurnover;
    }

    public void setAllTimeTurnover(BigDecimal allTimeTurnover) {
        this.allTimeTurnover = allTimeTurnover;
    }
}
