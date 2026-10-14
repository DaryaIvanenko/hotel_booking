package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Client;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    public final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client){
        return clientService.saveClient(client);
    }

    @DeleteMapping
    public void deleteAllClients(){
        clientService.deleteAllClients();
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id){
        clientService.deleteClientById(id);
    }
}
