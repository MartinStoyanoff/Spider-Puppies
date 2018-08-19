package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelecomServiceServiceImpl implements TelecomServiceService {

    private GenericRepository<TelecomService> telecomServiceRepository;

    @Autowired
    public TelecomServiceServiceImpl(GenericRepository<TelecomService> telecomServiceRepository) {
        this.telecomServiceRepository = telecomServiceRepository;
    }

    @Override
    public void addTelecomService(TelecomService telecomService) {
        telecomServiceRepository.create(telecomService);
    }

    @Override
    public TelecomService findTelecomServiceById(int id) {
        return telecomServiceRepository.findById(id);
    }

    @Override
    public List listAllTelecomServices() {
        return telecomServiceRepository.listAll();
    }

    @Override
    public void updateTelecomService(TelecomService telecomService) {
        telecomServiceRepository.update(telecomService);
    }

    @Override
    public void deleteTelecomService(int id) {
        telecomServiceRepository.delete(id);
    }
}
