package com.spidermanteam.spiderpuppies.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pin")
    private String personalIdentificationNumber;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy="subscriber")
    private List<Invoice> invoices;

    @ManyToMany(mappedBy = "subscriber")
    private List<TelecomService> telecomServices;

    @Column(name = "first_activation_date")
    private LocalDate firstServiceActivationDate;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;



    public Subscriber() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
