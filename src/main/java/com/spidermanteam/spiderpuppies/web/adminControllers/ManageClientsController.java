package com.spidermanteam.spiderpuppies.web.adminControllers;


import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/manageClients")
public class ManageClientsController {

    private ClientService clientService;

    @Autowired
    public ManageClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    @GetMapping("/findById/{id}")
    Client findClientById(@PathVariable int id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/listAll")
    List listAllClients() {
        return clientService.listAllClients();
    }

    @PutMapping("/update")
    void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    @DeleteMapping("/delete/{id}")
    void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }


}
