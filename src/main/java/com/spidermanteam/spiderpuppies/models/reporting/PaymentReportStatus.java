package com.spidermanteam.spiderpuppies.models.reporting;

public enum PaymentReportStatus {
  SUCCESSFULLY_PAID, FAILED_ALREADY_PAID, FAILED_INVOICE_OR_CLIENT_NOT_EXIST, FAILED_INVOICE_CURRENCY_NOT_SUPPORTED;
}