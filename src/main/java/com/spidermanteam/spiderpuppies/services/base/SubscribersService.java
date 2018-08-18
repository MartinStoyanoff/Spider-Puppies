package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.util.List;

public interface SubscribersService {

    void addSubscriber(Subscriber subscriber);

    Subscriber findSubscriberById(int id);

    List listAllSubscriber();

    void updateSubscriber (Subscriber subscriber);

    void deleteSubscriber (int id);
}
