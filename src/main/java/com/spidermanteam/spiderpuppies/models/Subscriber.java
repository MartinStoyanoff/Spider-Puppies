package com.spidermanteam.spiderpuppies.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pin")
    private String personalIdentificationNumber;

    @Column(name = "address")
    private String address;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subscriber")
    private List<Invoice> invoices;

    @ManyToMany
    @JoinTable(
            name = "services_subscribers",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<TelecomService> telecomServices;

    @Column(name = "first_activation_date")
    private LocalDate firstServiceActivationDate;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;

    @Column(name = "all_time_turnover")
    private BigDecimal allTimeTurnover;

    public Subscriber() {
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


    public BigDecimal getAllTimeTurnover() {
        return allTimeTurnover;
    }

    public void setAllTimeTurnover(BigDecimal allTimeTurnover) {
        this.allTimeTurnover = allTimeTurnover;
    }
}
