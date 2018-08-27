package com.spidermanteam.spiderpuppies.models.reporting;

public class PaymentReport {

    private String phoneNum;
    private int invoiceId;
    private PaymentReportStatus status;


    public PaymentReport() {
    }

    public PaymentReport(String phoneNum, int invoiceId, PaymentReportStatus status) {
        this.phoneNum = phoneNum;
        this.invoiceId = invoiceId;
        this.status = status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public PaymentReportStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentReportStatus status) {
        this.status = status;
    }
}
