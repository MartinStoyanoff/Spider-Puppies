package com.spidermanteam.spiderpuppies.services.clientaccess;

import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.*;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReport;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReportStatus;
import com.spidermanteam.spiderpuppies.services.ClientAccessServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientAccessServiceAllTests {

  @Mock
  private InvoiceRepository invoiceRepository;
  @Mock
  private SubscriberRepository subscriberRepository;

  private ClientAccessServiceImpl clientAccessService;

  private Client client;
  private TelecomService telecomService;
  private LocalDate date;
  private Subscriber subscriber;

  @Before
  public void beforeTest() {
    clientAccessService = new ClientAccessServiceImpl(invoiceRepository, subscriberRepository);
    client = new Client(new User("UserName", "Password", (byte) 1), "ClientFullName", "EIK");
    telecomService = new TelecomService("Type", "SubsPlan", BigDecimal.valueOf(0.00), new ArrayList<>());
    date = LocalDate.now();
    subscriber = new Subscriber("SubscPhone", "FirstName", "LastName", "PIN", "Address", new ArrayList<>(), new ArrayList<>(), date, date, client, BigDecimal.valueOf(0.00));
  }

  @Test
  public void payInvoiceByIdAndClientId_whenInvoiceIsPresented_ShouldReturnPaymentRepost(){

    Invoice invoice = new Invoice(subscriber,telecomService,BigDecimal.TEN,"BGN");
    invoice.setId(1);
    int clientId =(1);
    PaymentReport paymentReport = new PaymentReport();
    paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);

    when(invoiceRepository.findByIdAndClientId(1,1)).thenReturn(invoice);
    PaymentReport actualInvoice = clientAccessService.payInvoice(invoice,paymentReport);

    Assert.assertEquals("SUCCESSFULLY_PAID",paymentReport.getStatus()+"");

  }
  @Test
  public void payInvoice_whenPendingInvoiceIsPresented_ShouldReturnSuccessfulPaymentReport(){
    Invoice invoice = new Invoice(subscriber,telecomService,BigDecimal.TEN,"BGN");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice,paymentReport);

    Assert.assertEquals("SUCCESSFULLY_PAID",actualPaymentReport.getStatus()+"");
    verify(invoiceRepository, times(1)).update(invoice);
    verify(subscriberRepository, times(1)).update(subscriber);
  }


  @Test
  public void payInvoice_whenAlreadyPaidInvoiceIsPresented_ShouldReturnFailedAlreadyPaidPaymentReport(){
    Invoice invoice = new Invoice(subscriber,telecomService,BigDecimal.TEN,"BGN");
    invoice.setStatus("1");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice,paymentReport);

    Assert.assertEquals("FAILED_ALREADY_PAID",actualPaymentReport.getStatus()+"");
  }
  @Test
  public void payInvoice_whenInvoiceIsPresentedButCurrencyNotSupported_ShouldReturnCurrencyNotSupportedPaidPaymentReport(){
    Invoice invoice = new Invoice(subscriber,telecomService,BigDecimal.TEN,"DDD");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice,paymentReport);

    Assert.assertEquals("FAILED_INVOICE_CURRENCY_NOT_SUPPORTED",actualPaymentReport.getStatus()+"");
  }
  @Test
  public void payInvoice_whenInvoiceIsPresentedButInvoiceDetailsAreMissing_ShouldReturnCurrencyFailedStatusPaymentReport(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("BGN");
    PaymentReport paymentReport = new PaymentReport();
    paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice,paymentReport);

    Assert.assertEquals("FAILED_INVOICE_OR_CLIENT_NOT_EXIST",actualPaymentReport.getStatus()+"");
  }
}
