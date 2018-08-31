package com.spidermanteam.spiderpuppies.objectmapping;

import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MappingHelper {


    public static List<PaymentLine> mapSubscriberToPaymentLines(List<Subscriber> subscriberList){
        List<PaymentLine> paymentLines = new ArrayList<>();
        for (Subscriber sub: subscriberList) {

            List<TelecomService> telecomServices = sub.getTelecomServices();
            for (TelecomService ts : telecomServices) {
                int subscriberId = sub.getId();
                String subscriberPhone = sub.getPhone();
                String serviceType = ts.getType();
                String subscriptionPlan = ts.getSubscriptionPlan();
                BigDecimal price = ts.getPrice();
                String currency = "BGN";

                PaymentLine line = new PaymentLine(subscriberId, subscriberPhone, serviceType, subscriptionPlan, price, currency);
                paymentLines.add(line);
            }
        }
        return paymentLines;
    }
}