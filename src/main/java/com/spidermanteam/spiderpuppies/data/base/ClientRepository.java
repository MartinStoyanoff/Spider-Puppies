package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Client;

import java.util.List;

public interface ClientRepository {

    void addClient(Client client);

    List listAllClients();



}
