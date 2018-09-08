package com.spidermanteam.spiderpuppies.services.subscriber;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.*;
import com.spidermanteam.spiderpuppies.services.SubscriberServiceImpl;
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

@RunWith(MockitoJUnitRunner.class)
public class SubscribersServiceAllTests {

  @Mock
  SubscriberRepository subscriberRepository;
  @Mock
  GenericRepository<TelecomService> telecomServiceGenericRepository;
  @Mock
  InvoiceRepository invoiceRepository;

  private SubscriberServiceImpl subscribersService;

  @Before
  public void beforeTest() {
    subscribersService = new SubscriberServiceImpl(subscriberRepository, telecomServiceGenericRepository, invoiceRepository);
  }

  @Test
  public void addSubscriber_whenSubscriberIsPresented_ShouldInvokeCreateRepositoryMethod() {
    Subscriber subscriber = new Subscriber();

    doNothing().when(subscriberRepository).create(isA(Subscriber.class));
    subscribersService.addSubscriber(subscriber);

    verify(subscriberRepository, times(1)).create(subscriber);
  }


  @Test
  public void findSubscriberById_whenSubscriberIsPresented_ShouldReturnSubscriber() {

    Subscriber subscriber = new Subscriber();
    subscriber.setId(1);

    when(subscriberRepository.findById(1)).thenReturn(subscriber);
    Subscriber actualSubscriber = subscribersService.findSubscriberById(1);

    Assert.assertEquals(subscriber.getId(), actualSubscriber.getId());
  }

  @Test
  public void listAllSubscribers_whenSubscribersListIsPresented_ShouldReturnSubscribersList() {

    List<Subscriber> subscriberList = new ArrayList<>();
    Subscriber firstSubscriber = new Subscriber();
    Subscriber secondSubscriber = new Subscriber();
    Subscriber thirdSubscriber = new Subscriber();

    subscriberList.add(firstSubscriber);
    subscriberList.add(secondSubscriber);
    subscriberList.add(thirdSubscriber);

    when(subscriberRepository.listAll()).thenReturn(subscriberList);
    List<Subscriber> actualSubscriberList = subscribersService.listAllSubscribers();

    Assert.assertEquals(subscriberList.size(), actualSubscriberList.size());
  }

  @Test
  public void updateSubscriber_whenSubscriberIsPresented_ShouldInvokeUpdateRepositoryMethod() {

    Subscriber subscriber = new Subscriber();

    doNothing().when(subscriberRepository).update(isA(Subscriber.class));
    subscribersService.updateSubscriber(subscriber);
    verify(subscriberRepository, times(1)).update(subscriber);
  }

  @Test
  public void deletSubscriber_whenSubscriberIsPresented_ShouldInvokeDeleteRepositoryMethod() {

    doNothing().when(subscriberRepository).delete(isA(Integer.class));
    subscribersService.deleteSubscriber(1);

    verify(subscriberRepository, times(1)).delete(1);
  }

  @Test
  public void listAllForDefinedPeriod_whenDateRangeIsPresented_ShouldReturnSubscriberListForThisDateRange() {
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = startDate.plusMonths(1);
    Subscriber subscriber = new Subscriber();
    subscriber.setBillingDate(startDate.plusDays(3));

    List<Subscriber> subscriberList = new ArrayList<>();
    subscriberList.add(subscriber);

    when(subscriberRepository.findAllForDefinedPeriod(startDate, endDate)).thenReturn(subscriberList);
    List<Subscriber> actualSubscriberList = subscribersService.listAllForDefinedPeriod(startDate, endDate);

    Assert.assertTrue(actualSubscriberList.get(0).getBillingDate().isAfter(startDate));
    Assert.assertTrue(actualSubscriberList.get(0).getBillingDate().isBefore(endDate));
    Assert.assertEquals(subscriberList.size(), actualSubscriberList.size());
  }

  @Test
  public void getHighestPaidSumBySubscriber_whenSubscriberIsPresented_ShouldReturnHighestPaidSum() {
    BigDecimal highestSum = BigDecimal.valueOf(55.55);

    when(subscriberRepository.getHighestPaidSumBySubscriber(1)).thenReturn(highestSum);
    BigDecimal actualHighestSum = subscribersService.getHighestPaidSumBySubscriber(1);

    Assert.assertEquals(highestSum, actualHighestSum);
  }

  @Test
  public void getAveragePaidSumBySubscriber_whenSubscriberIsPresented_ShouldReturnAveragePaidSum() {
    BigDecimal averageSum = BigDecimal.valueOf(20.55);

    when(subscriberRepository.getHighestPaidSumBySubscriber(1)).thenReturn(averageSum);
    BigDecimal actualAverageSum = subscribersService.getHighestPaidSumBySubscriber(1);

    Assert.assertEquals(averageSum, actualAverageSum);
  }
  @Test
  public void addTelecomServiceToSubscriber_whenSubscriberIdAndTelecomServiceIdArePresented_ShouldAddTelecomServiceToSubscriber(){
    Client client = new Client(new User("UserName", "Password", (byte) 1), "ClientFullName", "EIK");
    TelecomService telecomService = new TelecomService("Type", "SubsPlan", BigDecimal.valueOf(10.00), new ArrayList<>());
    telecomService.setId(1);
    LocalDate date = LocalDate.now();
    Subscriber subscriber = new Subscriber("SubscPhone", "FirstName", "LastName", "PIN", "Address", new ArrayList<>(), new ArrayList<>(), date, date, client, BigDecimal.valueOf(0.00));
    subscriber.setId(1);
    subscriber.getTelecomServices().add(telecomService);

    when(subscriberRepository.findById(1)).thenReturn(subscriber);
    when(telecomServiceGenericRepository.findById(1)).thenReturn(telecomService);
    doNothing().when(invoiceRepository).create(isA(Invoice.class));

    subscribersService.addTelecomServiceToSubscriber(1,1);

    Assert.assertEquals(2,subscriber.getTelecomServices().size());
  }
}

//  @Override
//  public void addTelecomServiceToSubscriber(int subscriberId, int telecomServiceId) {
//    Subscriber subscriber = subscriberRepository.findById(subscriberId);
//    TelecomService telecomService = telecomServiceRepository.findById(telecomServiceId);
//    LocalDate billingDate = subscriber.getBillingDate();
//    LocalDate currentDay = LocalDate.now();
//    long daysForPay = DAYS.between(currentDay, billingDate);
//    LocalDate previousBillingDate = billingDate.minusMonths(1);
//    long daysUsedService = DAYS.between(billingDate, previousBillingDate);
//    List<TelecomService> telecomServices = subscriber.getTelecomServices();
//    for (TelecomService ts : telecomServices) {
//      BigDecimal telecomServicePricePerDay = ts.getPrice().divide(BigDecimal.valueOf(daysUsedService));
//      BigDecimal priceForInvoicing = telecomServicePricePerDay.multiply(BigDecimal.valueOf(daysForPay));
//      Invoice invoice = new Invoice(subscriber, telecomService, priceForInvoicing, "BGN");
//      subscriber.getInvoices().add(invoice);
//      invoiceRepository.create(invoice);
//      BigDecimal currentAllTimeTurnover = subscriber.getAllTimeTurnover();
//      subscriber.setAllTimeTurnover(currentAllTimeTurnover.add(priceForInvoicing));
//    }
//    subscriber.getTelecomServices().add(telecomService);
//    subscriber.setBillingDate(currentDay.plusMonths(1));
//  }
//
//
