package com.spidermanteam.spiderpuppies.web.adminControllers;


import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageClientsController {

    private ClientService clientService;

    @Autowired
    public ManageClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/admin/addClient")
    void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @GetMapping("/admin/findClientById/{id}")
    Client findClientById(@PathVariable("id") String id) {
        int clientId = Integer.parseInt(id);
        return clientService.findClientById(clientId);
    }

    @GetMapping("/admin/listAllClients")
    List listAllClients() {
        return clientService.listAllClients();
    }

    @PutMapping("/admin/updateClient")
    void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    @DeleteMapping("/admin/deleteClient/{id}")
    void deleteClient(@PathVariable("id") String id) {
        int clientId = Integer.parseInt(id);
        clientService.deleteClient(clientId);
    }


}