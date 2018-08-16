package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
        clientRepository.addClient(client);
    }

    @Override
    public List listAllClients() {
        return clientRepository.listAllClients();

    }
}
