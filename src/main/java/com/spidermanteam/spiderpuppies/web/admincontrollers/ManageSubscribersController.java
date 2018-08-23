package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberReport;
import com.spidermanteam.spiderpuppies.objectmapping.MappingHelper;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/manage/subscribers")
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
    SubscriberReport findSubscriberById(@PathVariable int id) {
        Subscriber subscriber = subscribersService.findSubscriberById(id);
        return MappingHelper.mapSubscriberToSubscriberReport(subscriber);
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

    @PostMapping("/listAllDuePayments")
    List<SubscriberReport> listAllForDefinedPeriod(@RequestBody List<LocalDate> dates){
        List<SubscriberReport> subscribersList= new ArrayList();
        LocalDate startDate = dates.get(0);
        LocalDate endDate = dates.get(1);
        List<Subscriber> subscribers = subscribersService.listAllForDefinedPeriod(startDate, endDate);
        for (Subscriber sub: subscribers) {
            SubscriberReport subscriberReport = MappingHelper.mapSubscriberToSubscriberReport(sub);
            subscribersList.add(subscriberReport);
        }
        return subscribersList;
    }
}
