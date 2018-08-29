package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class SubscriberServiceImpl implements SubscribersService {

    private SubscriberRepository subscriberRepository;
    private GenericRepository<TelecomService> telecomServiceRepository;
    private InvoiceRepository invoiceRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, GenericRepository<TelecomService> telecomServiceRepository, InvoiceRepository invoiceRepository) {
        this.subscriberRepository = subscriberRepository;
        this.telecomServiceRepository = telecomServiceRepository;
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscriberRepository.create(subscriber);
    }

    @Override
    public Subscriber findSubscriberById(int id) {
        Subscriber subscriber = subscriberRepository.findById(id);
        List<Invoice> invoiceList = subscriber.getInvoices();
        return subscriberRepository.findById(id);
    }

    @Override
    public List listAllSubscribers() {
        return subscriberRepository.listAll();
    }

    @Override
    public void updateSubscriber(Subscriber subscriber) {
        subscriberRepository.update(subscriber);

    }

    @Override
    public void deleteSubscriber(int id) {
        subscriberRepository.delete(id);
    }

    @Override
    public List listAllForDefinedPeriod(LocalDate start, LocalDate end) {
        return subscriberRepository.findAllForDefinedPeriod(start, end);
    }

    @Override
    public BigDecimal getHighestPaidSumBySubscriber(int id) {
        return subscriberRepository.getHighestPaidSumBySubscriber(id);
    }

    @Override
    public BigDecimal getAveragePaidSumBySubscriber(int id) {
        return subscriberRepository.getAveragePaidSumBySubscriber(id);
    }

    @Override
    public void addTelecomServiceToSubscriber(int subscriberId, int telecomServiceId
    ) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId);
        TelecomService telecomService = telecomServiceRepository.findById(telecomServiceId);
        LocalDate billingDate = subscriber.getBillingDate();
        LocalDate currentDay = LocalDate.now();
        long daysForPay = DAYS.between(currentDay, billingDate);
        LocalDate previousBillingDate = billingDate.minusMonths(1);
        long daysUsedService = DAYS.between(billingDate, previousBillingDate);

        List<TelecomService> telecomServices = subscriber.getTelecomServices();
        for (TelecomService ts:telecomServices
             ) {

            BigDecimal telecomServicePricePerDay = ts.getPrice().divide(BigDecimal.valueOf(daysUsedService));
            BigDecimal priceForInvoicing = telecomServicePricePerDay.multiply(BigDecimal.valueOf(daysForPay));

            Invoice invoice = new Invoice(subscriber,telecomService,priceForInvoicing,"BGN");
            subscriber.getInvoices().add(invoice);
            invoiceRepository.create(invoice);
            BigDecimal currentAllTimeTurnover = subscriber.getAllTimeTurnover();
            subscriber.setAllTimeTurnover(currentAllTimeTurnover.add(priceForInvoicing));

        }
        subscriber.getTelecomServices().add(telecomService);

        subscriber.setBillingDate(currentDay.plusMonths(1));

    }

}
