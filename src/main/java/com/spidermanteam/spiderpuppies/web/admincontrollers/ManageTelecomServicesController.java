package com.spidermanteam.spiderpuppies.web.admincontrollers;

import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/manage/telecomServices")
public class ManageTelecomServicesController {

    private TelecomServiceService telecomServiceService;

    @Autowired
    public ManageTelecomServicesController(TelecomServiceService telecomServiceService) {
        this.telecomServiceService = telecomServiceService;
    }

    @PostMapping("/add")
    void addTelecomService(@RequestBody TelecomService telecomService) {
        telecomServiceService.addTelecomService(telecomService);
    }

    @GetMapping("/findById/{id}")
    TelecomService findTelecomServiceById(@PathVariable("id") String id) {
        int telecomServiceId = Integer.parseInt(id);
        return telecomServiceService.findTelecomServiceById(telecomServiceId);
    }

    @GetMapping("/listAll")
    List listAllTelecomServices() {
        return telecomServiceService.listAllTelecomServices();
    }

    @PutMapping("/update")
    void updateTelecomService(@RequestBody TelecomService telecomService) {
        telecomServiceService.updateTelecomService(telecomService);
    }

    @DeleteMapping("/delete/{id}")
    void deleteTelecomService(@PathVariable("id") String id) {
        int telecomServiceId = Integer.parseInt(id);
        telecomServiceService.deleteTelecomService(telecomServiceId);
    }
}
