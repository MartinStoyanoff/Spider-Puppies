package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SubscribersService {

    void addSubscriber(Subscriber subscriber);

    Subscriber findSubscriberById(int id);

    List listAllSubscribers();

    void updateSubscriber (Subscriber subscriber);

    void deleteSubscriber (int id);

    List listAllForDefinedPeriod(LocalDate start, LocalDate end);

    BigDecimal getHighestPaidSumBySubscriber(int id);

    BigDecimal getAveragePaidSumBySubscriber(int id);

    void addTelecomServiceToSubscriber(int subscriberId, int telecomServiceId);

}
