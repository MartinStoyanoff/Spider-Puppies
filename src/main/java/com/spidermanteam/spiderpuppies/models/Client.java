package com.spidermanteam.spiderpuppies.models;


import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "uic")
    private String unifiedIdentificationCode;


    public Client() {
    }

    public Client(User user, String fullName, String unifiedIdentificationCode) {
        this.user = user;
        this.fullName = fullName;
        this.unifiedIdentificationCode = unifiedIdentificationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUnifiedIdentificationCode() {
        return unifiedIdentificationCode;
    }

    public void setUnifiedIdentificationCode(String unifiedIdentificationCode) {
        this.unifiedIdentificationCode = unifiedIdentificationCode;
    }
}
