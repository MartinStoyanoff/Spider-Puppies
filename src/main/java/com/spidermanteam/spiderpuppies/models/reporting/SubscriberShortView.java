package com.spidermanteam.spiderpuppies.models.reporting;

import java.math.BigDecimal;

public class SubscriberShortView {

    private String phone;
    private String name;
    private BigDecimal avgPerMonth;
    private BigDecimal allTimeTurnover;

    public SubscriberShortView(String phone, String name, BigDecimal avgPerMonth, BigDecimal allTimeTurnover) {
        this.phone = phone;
        this.name = name;
        this.avgPerMonth = avgPerMonth;
        this.allTimeTurnover = allTimeTurnover;
    }

    public SubscriberShortView() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAvgPerMonth() {
        return avgPerMonth;
    }

    public void setAvgPerMonth(BigDecimal avgPerMonth) {
        this.avgPerMonth = avgPerMonth;
    }

    public BigDecimal getAllTimeTurnover() {
        return allTimeTurnover;
    }

    public void setAllTimeTurnover(BigDecimal allTimeTurnover) {
        this.allTimeTurnover = allTimeTurnover;
    }
}
