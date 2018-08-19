package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.TelecomService;

import java.util.List;

public interface TelecomServiceService {
    void addTelecomService(TelecomService telecomService);

    TelecomService findTelecomServiceById(int id);

    List listAllTelecomServices();

    void deleteTelecomService(int id);

    void updateTelecomService(TelecomService telecomService);

}
