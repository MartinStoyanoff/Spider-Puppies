package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Client;

import java.util.List;

public interface ClientService {
    void addClient (Client client);

    List listAllClients();

    void deleteClient(int id);

    void updateClient(Client client);

    Client findClientById(int id);


}
