package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Client;

import java.util.List;

public interface ClientService {
    void addClient (Client client);

    List listAllClients();
}
