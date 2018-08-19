package com.spidermanteam.spiderpuppies.web;

import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TelecomServiceController {

    private TelecomServiceService telecomServiceService;

    @Autowired
    public TelecomServiceController(TelecomServiceService telecomServiceService) {
        this.telecomServiceService = telecomServiceService;
    }

    @PostMapping("/admin/addTelecomService")
    void addTelecomService(@RequestBody TelecomService telecomService) {
        telecomServiceService.addTelecomService(telecomService);
    }

    @GetMapping("/admin/findTelecomService/{id}")
    TelecomService findTelecomServiceById(@PathVariable("id") String id) {
        int telecomServiceId = Integer.parseInt(id);
        return telecomServiceService.findTelecomServiceById(telecomServiceId);
    }

    @GetMapping("/admin/listAllTelecomServices")
    List listAllTelecomServices() {
        return telecomServiceService.listAllTelecomServices();
    }

    @PutMapping("/admin/updateTelecomService")
    void updateTelecomService(@RequestBody TelecomService telecomService) {
        telecomServiceService.updateTelecomService(telecomService);
    }

    @DeleteMapping("/admin/deleteTelecomService/{id}")
    void deleteTelecomService(@PathVariable("id") String id) {
        int telecomServiceId = Integer.parseInt(id);
        telecomServiceService.deleteTelecomService(telecomServiceId);
    }
}
