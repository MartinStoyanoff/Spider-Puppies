package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.time.LocalDate;
import java.util.List;

public interface SubscribersService {

    void addSubscriber(Subscriber subscriber);

    Subscriber findSubscriberById(int id);

    List listAllSubscribers();

    void updateSubscriber (Subscriber subscriber);

    void deleteSubscriber (int id);

    List listAllForDefinedPeriod(LocalDate start, LocalDate end);
}
