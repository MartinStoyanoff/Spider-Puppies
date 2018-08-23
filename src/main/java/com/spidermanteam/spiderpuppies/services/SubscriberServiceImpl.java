package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscribersService {

    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
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
}
