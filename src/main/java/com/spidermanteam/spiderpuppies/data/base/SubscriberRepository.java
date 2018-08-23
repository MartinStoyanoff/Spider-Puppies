package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.time.LocalDate;
import java.util.List;

public interface SubscriberRepository extends GenericRepository<Subscriber> {
    List<Subscriber> findAllForDefinedPeriod (LocalDate start, LocalDate end);


}
