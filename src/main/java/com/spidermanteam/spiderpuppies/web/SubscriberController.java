package com.spidermanteam.spiderpuppies.web;

import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriberController {

    private SubscribersService subscribersService;

    @Autowired
    public SubscriberController(SubscribersService subscribersService) {
        this.subscribersService = subscribersService;
    }

    @PostMapping("/admin/addSubscriber")
    void addSubscriber(@RequestBody Subscriber subscriber) {
        subscribersService.addSubscriber(subscriber);
    }

    @GetMapping("/admin/findSubscriberById/{id}")
    Subscriber findSubscriberById(@PathVariable("id") String id) {
        int subscriberId = Integer.parseInt(id);
        return subscribersService.findSubscriberById(subscriberId);
    }

    @GetMapping("/admin/listAllSubscribers")
    List listAllSubscribers() {
        return subscribersService.listAllSubscribers();
    }

    @PutMapping("/admin/updateSubscriber")
    void updateSubscriber(@RequestBody Subscriber subscriber) {
        subscribersService.updateSubscriber(subscriber);
    }

    @DeleteMapping("/admin/deleteSubscriber/{id}")
    void deleteSubscriber(@PathVariable("id") String id) {
        int subscriberId = Integer.parseInt(id);
        subscribersService.deleteSubscriber(subscriberId);
    }
}
