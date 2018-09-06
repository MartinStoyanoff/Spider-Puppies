package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SubscriberRepository extends GenericRepository<Subscriber> {

    List<Subscriber> findAllForDefinedPeriod (LocalDate start, LocalDate end);

    BigDecimal getHighestPaidSumBySubscriber (int id);

    BigDecimal getAveragePaidSumBySubscriber (int id);

    Subscriber getSubscriberByPhoneAndClientId(String phone, int clientId);

    List<Subscriber> getTenBestSubscribersByTurnoverAndClientId(int clientId);

    List<Subscriber> getTenBestSubscribersByTurnover();

    List<Subscriber> getAllSubscribersWithPendingInvoiceByClientId(int clientId);

    Subscriber getSubscriberByPhone(String phone);



}
