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

  @PutMapping("/invoices/payById")
  public PaymentReport payInvoiceById(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    int invoiceId = Integer.parseInt(request.getHeader("invoiceId"));
    return clientAccessService.payInvoiceByIdAndClientId(invoiceId, clientId);
  }

  @PutMapping("/invoices/payByPhone")
  public List<PaymentReport> payInvoiceByPhone(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    String subscribersPhone = request.getHeader("phone");
    return clientAccessService.payInvoicesByPhoneAndClientId(subscribersPhone, clientId);
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

  @GetMapping("/invoice/findAllPendingById")
  List<InvoiceView> findAllPendingInvoicesByClientId(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.listAllPendingInvoicesByClientId(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      InvoiceView invoiceView = new InvoiceView(inv);
      invoiceViewList.add(invoiceView);
    }

    return invoiceViewList;
  }

  @GetMapping("/invoice/findAllById")
  List<InvoiceView> findAllInvoicesByClientId(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.listAllInvoicesByClientId(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      InvoiceView invoiceView = new InvoiceView(inv);
      invoiceViewList.add(invoiceView);
    }
    return invoiceViewList;
  }

  @GetMapping("/invoices/findDueInvoice")
  List<InvoiceView> findDueInvoicesByPhone(HttpServletRequest request) {
    String subscribersPhone = request.getHeader("phone");
    List<Invoice> invoiceList = clientAccessService.findDueInvoicesByPhone(subscribersPhone);
    List<InvoiceView> invoiceViewList = new ArrayList<>();

    for (Invoice inv : invoiceList) {
      invoiceViewList.add(new InvoiceView(inv));
    }
    return invoiceViewList;
  }

  @GetMapping("/invoices/getLastTenPaid")
  List<InvoiceView> invoiceViewList(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoiceList = clientAccessService.getLastTenPaidInvoiceByClient(clientId);
    List<InvoiceView> invoiceViewList = new ArrayList<>();
    for (Invoice inv : invoiceList) {
      invoiceViewList.add(new InvoiceView(inv));
    }
    return invoiceViewList;
  }

  @GetMapping("/subscribers/getTenBest")
  List<SubscriberShortView> getTenBestSubscribersByTurnoverAndClientId(HttpServletRequest request) {
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

  @GetMapping("/subscribers/getAllWithPendingInvoice")
  HashSet<String> getAllWithPendingInvoice(HttpServletRequest request) {
    int clientId = jwtParser.getClientIdByUsernameFromToken(request);
    List<Invoice> invoices = clientAccessService.listAllPendingInvoicesByClientId(clientId);
    HashSet<String> subscriberPhoneNumbers = new HashSet<>();

    for (Invoice inv : invoices) {
      subscriberPhoneNumbers.add(inv.getSubscriber().getPhone());
    }
    return subscriberPhoneNumbers;
  }

  @GetMapping("/subscribers/findSubscriberFullInfoByPhone//{id}/{phone}")
  SubscriberView findSubscriberFullInfoByPhoneAndClientId(@RequestParam int subscriberId, @RequestParam String subscriberPhone) {
    Subscriber subscriber = clientAccessService.getSubscriberByPhoneAndClientId(subscriberPhone,subscriberId);
    return new SubscriberView(subscriber);
  }
}



