package com.spidermanteam.spiderpuppies.web.clientcontrollers;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceView;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReport;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberShortView;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberView;
import com.spidermanteam.spiderpuppies.security.JwtParser;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_CLIENT')")
@RequestMapping("/api")
public class ClientController {

  private ClientAccessService clientAccessService;
  private JwtParser jwtParser;

  @Autowired
  public ClientController(ClientAccessService clientAccessService, JwtParser jwtParser) {
    this.clientAccessService = clientAccessService;
    this.jwtParser = jwtParser;
  }

  @PutMapping("/invoices/payById/{invoiceId}")
  public PaymentReport payInvoiceById(@PathVariable int invoiceId, HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    return clientAccessService.payInvoiceByIdAndClientId(invoiceId, clientId);
  }


  @PutMapping("/invoices/payByPhone/{phone}")
  public List<PaymentReport> payInvoiceByPhone(@RequestParam String phone, HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    return clientAccessService.payInvoicesByPhoneAndClientId(phone, clientId);
  }


  @PutMapping("/invoice/payByIdList")
  public List<PaymentReport> payInvoicesByIdList(@RequestBody List<Integer> idList, HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    return clientAccessService.payInvoicesByIdListAndClientId(idList, clientId);
  }

  @PutMapping("/invoice/payByPhoneList")
  public List<PaymentReport> payInvoicesByPhoneList(@RequestBody List<String> phonesList, HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    return clientAccessService.payInvoicesByPhoneListAndClientId(phonesList, clientId);
  }

  @GetMapping("/invoice/listAllPendingByClientId")
  List<InvoiceView> listAllPendingInvoicesByClientId(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.listAllPendingInvoicesByClientId(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      InvoiceView invoiceView = new InvoiceView(inv);
      invoiceViewList.add(invoiceView);
    }
    return invoiceViewList;
  }

  @GetMapping("/invoice/listAllByClientId")
  List<InvoiceView> listAllInvoicesByClientId(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.listAllInvoicesByClientId(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      InvoiceView invoiceView = new InvoiceView(inv);
      invoiceViewList.add(invoiceView);
    }
    return invoiceViewList;
  }

  @GetMapping("/invoices/listDueByPhone/{phone}")
  List<InvoiceView> listDueInvoicesByPhone(@PathVariable String phone) {
    List<Invoice> invoiceList = clientAccessService.findDueInvoicesByPhone(phone);
    List<InvoiceView> invoiceViewList = new ArrayList<>();

    for (Invoice inv : invoiceList) {
      invoiceViewList.add(new InvoiceView(inv));
    }
    return invoiceViewList;
  }

  @GetMapping("/invoices/listLastTenPaid")
  List<InvoiceView> listLastTenPaidInvoice(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.getLastTenPaidInvoiceByClient(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      invoiceViewList.add(new InvoiceView(inv));
    }
    return invoiceViewList;
  }

  @GetMapping("/subscribers/listTenBest")
  List<SubscriberShortView> listTenBestSubscribersByTurnoverAndClientId(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Subscriber> subscriberList = clientAccessService.getTenBestSubscribersByTurnoverAndClientId(clientId);
    List<SubscriberShortView> bestTenSubscribersList = new ArrayList<>();
    for (Subscriber sub : subscriberList) {
      String name = sub.getFirstName() + " " + sub.getLastName();
      BigDecimal avgPerMonth = clientAccessService.getAvgPriceBySubscriberId(sub.getId());
      SubscriberShortView subReport = new SubscriberShortView(sub.getPhone(), name, avgPerMonth, sub.getAllTimeTurnover());
      bestTenSubscribersList.add(subReport);
    }
    return bestTenSubscribersList;
  }

  @GetMapping("/subscribers/getAllPhoneNumbersWithPendingInvoice")
  HashSet<String> getAllPhoneNumbersWithPendingInvoice(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoices = clientAccessService.listAllPendingInvoicesByClientId(clientId);
    HashSet<String> subscriberPhoneNumbers = new HashSet<>();

    for (Invoice inv : invoices) {
      subscriberPhoneNumbers.add(inv.getSubscriber().getPhone());
    }
    return subscriberPhoneNumbers;
  }

  @GetMapping("/subscribers/getSubscriberFullInfoByPhone/{phone}")
  SubscriberView getSubscriberFullInfoByPhoneAndClientId(@RequestParam String subscribersPhone, HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    Subscriber subscriber = clientAccessService.getSubscriberByPhoneAndClientId(subscribersPhone, clientId);
    return new SubscriberView(subscriber);
  }
}