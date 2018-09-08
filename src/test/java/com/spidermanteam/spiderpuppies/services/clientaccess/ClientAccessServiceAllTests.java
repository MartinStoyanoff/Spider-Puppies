package com.spidermanteam.spiderpuppies.services.clientaccess;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import java.util.List;


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
    client.setId(1);
    telecomService = new TelecomService("Type", "SubsPlan", BigDecimal.valueOf(0.00), new ArrayList<>());
    date = LocalDate.now();
    subscriber = new Subscriber("088", "FirstName", "LastName", "PIN", "Address", new ArrayList<>(), new ArrayList<>(), date, date, client, BigDecimal.valueOf(0.00));
  }

  @Test
  public void payInvoiceByIdAndClientId_whenInvoiceIsPresented_ShouldReturnPaymentRepost() {

    Invoice invoice = new Invoice(subscriber, telecomService, BigDecimal.TEN, "BGN");
    invoice.setId(1);
    int clientId = (1);
    PaymentReport paymentReport = new PaymentReport();
    paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);

    when(invoiceRepository.findByIdAndClientId(1, 1)).thenReturn(invoice);
    PaymentReport actualInvoice = clientAccessService.payInvoice(invoice, paymentReport);

    Assert.assertEquals("SUCCESSFULLY_PAID", paymentReport.getStatus() + "");

  }

  @Test
  public void payInvoicesByPhoneAndClientId_whenInvoiceIsPresented_ShouldReturnPaymentRepostList() {

    Invoice invoice = new Invoice(subscriber, telecomService, BigDecimal.TEN, "BGN");
    invoice.setId(1);
    invoice.setStatus("0");
    int clientId = (1);
    List<Invoice> invoiceList = new ArrayList<>();
    invoiceList.add(invoice);

    PaymentReport paymentReport = new PaymentReport();
    paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    when(invoiceRepository.findInvoicesByPhone("088")).thenReturn(invoiceList);
    List<PaymentReport> paymentReportList = clientAccessService.payInvoicesByPhoneAndClientId("088", 1);

    Assert.assertEquals(invoiceList.size(), paymentReportList.size());

  }

  @Test
  public void payInvoice_whenPendingInvoiceIsPresented_ShouldReturnSuccessfulPaymentReport() {
    Invoice invoice = new Invoice(subscriber, telecomService, BigDecimal.TEN, "BGN");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice, paymentReport);

    Assert.assertEquals("SUCCESSFULLY_PAID", actualPaymentReport.getStatus() + "");
    verify(invoiceRepository, times(1)).update(invoice);
    verify(subscriberRepository, times(1)).update(subscriber);
  }

  @Test
  public void payInvoice_whenAlreadyPaidInvoiceIsPresented_ShouldReturnFailedAlreadyPaidPaymentReport() {
    Invoice invoice = new Invoice(subscriber, telecomService, BigDecimal.TEN, "BGN");
    invoice.setStatus("1");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice, paymentReport);

    Assert.assertEquals("FAILED_ALREADY_PAID", actualPaymentReport.getStatus() + "");
  }

  @Test
  public void payInvoice_whenInvoiceIsPresentedButCurrencyNotSupported_ShouldReturnCurrencyNotSupportedPaidPaymentReport() {
    Invoice invoice = new Invoice(subscriber, telecomService, BigDecimal.TEN, "DDD");
    PaymentReport paymentReport = new PaymentReport();

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice, paymentReport);

    Assert.assertEquals("FAILED_INVOICE_CURRENCY_NOT_SUPPORTED", actualPaymentReport.getStatus() + "");
  }

  @Test
  public void payInvoice_whenInvoiceIsPresentedButInvoiceDetailsAreMissing_ShouldReturnCurrencyFailedStatusPaymentReport() {
    Invoice invoice = new Invoice();
    invoice.setCurrency("BGN");
    PaymentReport paymentReport = new PaymentReport();
    paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);

    doNothing().when(invoiceRepository).update(isA(Invoice.class));
    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    PaymentReport actualPaymentReport = clientAccessService.payInvoice(invoice, paymentReport);

    Assert.assertEquals("FAILED_INVOICE_OR_CLIENT_NOT_EXIST", actualPaymentReport.getStatus() + "");
  }

  @Test
  public void listAllPendingInvoicesByClientId_whenClientIdIsPresented_ShouldReturnInvoiceList() {
    List<Invoice> invoiceList = new ArrayList<>();
    invoiceList.add(new Invoice());
    invoiceList.add(new Invoice());

    when(invoiceRepository.findAllPendingInvoicesByClientId(1)).thenReturn(invoiceList);
    List<Invoice> actualInvoiceList = clientAccessService.listAllPendingInvoicesByClientId(1);

    Assert.assertEquals(invoiceList.size(), actualInvoiceList.size());
  }

  @Test
  public void listAllInvoicesByClientId_whenClientIdIsPresented_ShouldReturnInvoiceList() {

    List<Invoice> invoiceList = new ArrayList<>();
    invoiceList.add(new Invoice());


    when(invoiceRepository.findAllPendingInvoicesByClientId(1)).thenReturn(invoiceList);
    List<Invoice> actualInvoiceList = clientAccessService.listAllPendingInvoicesByClientId(1);

    Assert.assertEquals(invoiceList.size(), actualInvoiceList.size());
  }

  @Test
  public void getSubscriberByPhoneAndClientId_whenSubscriberIdIsPresented_ShouldReturnInvoiceList() {
    Subscriber subscriber = new Subscriber();
    subscriber.setId(1);

    when(subscriberRepository.getSubscriberByPhoneAndClientId("088", 1)).thenReturn(subscriber);
    Subscriber actualSubscriber = clientAccessService.getSubscriberByPhoneAndClientId("088", 1);

    Assert.assertEquals(1, actualSubscriber.getId());
  }

  @Test
  public void getMaxPriceBySubscriberId_whenSubscriberIdIsPresented_ShouldReturnMaxPrice() {
    BigDecimal maxPrice = new BigDecimal(22.22);

    when(subscriberRepository.getHighestPaidSumBySubscriber(1)).thenReturn(maxPrice);

    BigDecimal actualPrice = clientAccessService.getMaxPriceBySubscriberId(1);

    Assert.assertEquals(new BigDecimal(22.22), actualPrice);
  }

  @Test
  public void getLastTenPaidInvoiceBySubscriberId_whenSubscriberIdIsPresented_ShouldReturnInvoiceList() {

    List<Invoice> invoiceList = new ArrayList<>();
    invoiceList.add(new Invoice());
    invoiceList.add(new Invoice());

    when(invoiceRepository.findLastTenPaymentsBySubscriberId(1)).thenReturn(invoiceList);
    List<Invoice> actualInvoiceList = clientAccessService.getLastTenPaidInvoiceBySubscriberId(1);

    Assert.assertEquals(invoiceList.size(), actualInvoiceList.size());
  }

  @Test
  public void getLastTenPaidInvoiceByClientId_whenSubscriberIdIsPresented_ShouldReturnInvoiceList() {

    List<Invoice> invoiceList = new ArrayList<>();
    invoiceList.add(new Invoice());
    invoiceList.add(new Invoice());
    invoiceList.add(new Invoice());

    when(invoiceRepository.findLastTenPaymentsByClientId(1)).thenReturn(invoiceList);
    List<Invoice> actualInvoiceList = clientAccessService.getLastTenPaidInvoiceByClient(1);

    Assert.assertEquals(invoiceList.size(), actualInvoiceList.size());
  }

  @Test
  public void getTenBestSubscribersByTurnoverAndClientId_whenClientIdIsPresented_ShouldReturnSubscriberList() {

    List<Subscriber> subscriberList = new ArrayList<>();
    subscriberList.add(new Subscriber());
    subscriberList.add(new Subscriber());

    when(subscriberRepository.getTenBestSubscribersByTurnoverAndClientId(1)).thenReturn(subscriberList);
    List<Subscriber> actualSubscribersList = clientAccessService.getTenBestSubscribersByTurnoverAndClientId(1);

    Assert.assertEquals(subscriberList.size(), actualSubscribersList.size());
  }

  @Test
  public void getTenBestSubscribersByTurnover_whenSubscriberIsPresented_ShouldReturnSubscribersList() {
    List<Subscriber> subscriberList = new ArrayList<>();
    subscriberList.add(new Subscriber());
    subscriberList.add(new Subscriber());
    subscriberList.add(new Subscriber());
    subscriberList.add(new Subscriber());

    when(subscriberRepository.getTenBestSubscribersByTurnover()).thenReturn(subscriberList);
    List<Subscriber> actualSubscriberList = clientAccessService.getTenBestSubscribersByTurnover();

    Assert.assertEquals(subscriberList.size(), actualSubscriberList.size());
  }

  @Test
  public void currencyCheck_whenInvoiceCurrencyIsBgn_ShouldReturnTrue(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("BGN");

    boolean check = clientAccessService.currencyCheck(invoice);

    Assert.assertTrue(check);

  }
  @Test
  public void currencyCheck_whenInvoiceCurrencyIsNotBgn_ShouldReturnFalse(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("DDD");

    boolean check = clientAccessService.currencyCheck(invoice);

    Assert.assertFalse(check);
  }
  @Test
  public void invoiceCurrencyConverter_whenInvoiceCurrencyIsEur_ShouldConvertedToBgnInvoice(){
  Invoice invoice = new Invoice();
  invoice.setCurrency("EUR");
  invoice.setPrice(BigDecimal.valueOf(10));

  clientAccessService.invoiceCurrencyConverter(invoice);

  Assert.assertEquals("BGN",invoice.getCurrency());
  Assert.assertEquals("19.55830",invoice.getPrice()+"");

  }
  @Test
  public void invoiceCurrencyConverter_whenInvoiceCurrencyIsGbp_ShouldConvertedToBgnInvoice(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("GBP");
    invoice.setPrice(BigDecimal.valueOf(10));

    clientAccessService.invoiceCurrencyConverter(invoice);

    Assert.assertEquals("BGN",invoice.getCurrency());
    Assert.assertEquals("21.68330",invoice.getPrice()+"");

  }
  @Test
  public void invoiceCurrencyConverter_whenInvoiceCurrencyIsUsd_ShouldConvertedToBgnInvoice(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("USD");
    invoice.setPrice(BigDecimal.valueOf(10));

    clientAccessService.invoiceCurrencyConverter(invoice);

    Assert.assertEquals("BGN",invoice.getCurrency());
    Assert.assertEquals("16.87810",invoice.getPrice()+"");

  }

  @Test
  public void invoiceCurrencyConverter_whenInvoiceCurrencyIsChf_ShouldConvertedToBgnInvoice(){
    Invoice invoice = new Invoice();
    invoice.setCurrency("CHF");
    invoice.setPrice(BigDecimal.valueOf(10));

    clientAccessService.invoiceCurrencyConverter(invoice);

    Assert.assertEquals("BGN",invoice.getCurrency());
    Assert.assertEquals("17.15940",invoice.getPrice()+"");

  }
}