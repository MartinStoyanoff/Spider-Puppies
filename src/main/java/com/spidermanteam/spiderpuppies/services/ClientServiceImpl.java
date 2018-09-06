package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientRepository;
import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void addClient(Client client) {
        clientRepository.create(client);
    }

    @Override
    public Client findClientById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public List listAllClients() {
        return clientRepository.listAll();

    }

    @Override
    public void deleteClient(int id) {
        clientRepository.delete(id);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    @Override
    public Client findClientByUserUsername(String username) {
        return clientRepository.findClientByUserUserName(username);
    }

}
