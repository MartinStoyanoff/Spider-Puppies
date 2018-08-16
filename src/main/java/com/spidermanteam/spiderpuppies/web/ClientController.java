package com.spidermanteam.spiderpuppies.web;


import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/admin/add")
    void addClient(Client client){
        clientService.addClient(client);
    }

    @GetMapping("/admin/listAll")
    List listAllClients(){
       return clientService.listAllClients();
    }
}
