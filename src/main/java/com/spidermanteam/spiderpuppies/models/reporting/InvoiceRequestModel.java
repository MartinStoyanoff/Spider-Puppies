package com.spidermanteam.spiderpuppies.models.reporting;

public class InvoiceRequestModel {

  private String subscriberId;
  private String currency;

  public InvoiceRequestModel() {
  }

  public InvoiceRequestModel(String subscriberId, String currency) {
    this.subscriberId = subscriberId;
    this.currency = currency;
  }

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
