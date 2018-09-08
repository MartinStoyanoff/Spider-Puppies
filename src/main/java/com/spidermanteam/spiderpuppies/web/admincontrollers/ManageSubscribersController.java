package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentLine;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberView;
import com.spidermanteam.spiderpuppies.objectmapping.MappingHelper;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/subscribers")
public class ManageSubscribersController {

  private SubscribersService subscribersService;

  @Autowired
  public ManageSubscribersController(SubscribersService subscribersService) {
    this.subscribersService = subscribersService;
  }

  @PostMapping("/add")
  void addSubscriber(@RequestBody Subscriber subscriber) {
    subscribersService.addSubscriber(subscriber);
  }

  @GetMapping("/findById/{id}")
  SubscriberView findSubscriberById(@PathVariable int id) {
    Subscriber subscriber = subscribersService.findSubscriberById(id);
    return new SubscriberView(subscriber);
  }

  @GetMapping("/listAll")
  List listAllSubscribers() {
    return subscribersService.listAllSubscribers();
  }

  @PutMapping("/update")
  void updateSubscriber(@RequestBody Subscriber subscriber) {
    subscribersService.updateSubscriber(subscriber);
  }

  @DeleteMapping("/delete/{id}")
  void deleteSubscriber(@PathVariable int id) {
    subscribersService.deleteSubscriber(id);
  }

  /*
   * /admin/bulkpayment
   *
   * /admin/singlepayment - Load Button -
   *
   * */
  @PostMapping("/listAllDuePayments")
  List listAllForDefinedPeriod(@RequestBody List<String> dates) {
    List<PaymentLine> subscribersList = new ArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    LocalDate startDate = LocalDate.parse(dates.get(0), formatter);
    LocalDate endDate = LocalDate.parse(dates.get(1), formatter);
    List subscribers = subscribersService.listAllForDefinedPeriod(startDate, endDate);

    return MappingHelper.mapSubscriberToPaymentLines(subscribers);
  }

  @GetMapping("/getMaxAmountById/{id}")
  public BigDecimal getHighestPaidSumBySubscriber(@PathVariable int id) {
    return subscribersService.getHighestPaidSumBySubscriber(id);
  }

  @GetMapping("/getAvgAmountById/{id}")
  public BigDecimal getAveragePaidSumBySubscriber(@PathVariable int id) {
    return subscribersService.getAveragePaidSumBySubscriber(id);
  }

  @PostMapping("/listAllPhoneNumbersInBillingPeriod")
  public List<String> getAllSubscribersInBillingPeriod(@RequestBody List<String> dates) {
    List<String> subscribersList = new ArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate startDate = LocalDate.parse(dates.get(0), formatter);
    LocalDate endDate = LocalDate.parse(dates.get(1), formatter);
    List<Subscriber> subscribers = subscribersService.listAllForDefinedPeriod(startDate, endDate);

    for (Subscriber sub : subscribers) {
      String phone = sub.getPhone();
      subscribersList.add(phone);
    }
    return subscribersList;
  }

  @GetMapping("/listDuePaymentsByPhone/{phone}")
  List<PaymentLine> getSubscriberDuePaymentsByPhone(@PathVariable String phone) {
    Subscriber subscriber = subscribersService.getSubscriberByPhone(phone);
    List<Subscriber> subscribers = new ArrayList<>();
    subscribers.add(subscriber);
    return MappingHelper.mapSubscriberToPaymentLines(subscribers);
  }
  @GetMapping("/subscribers/getSubscriberByPhone/{phone}")
  SubscriberView getSubscriberFullInfoByPhoneAndClientId(@PathVariable String phone) {
    Subscriber subscriber = subscribersService.getSubscriberByPhone(phone);
    return new SubscriberView(subscriber);
  }
}
