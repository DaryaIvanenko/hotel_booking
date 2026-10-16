package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Client;
import org.example.service.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Client createClient(@Valid @RequestBody Client client){
        return clientService.saveClient(client);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllClients(){
        clientService.deleteAllClients();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteClientById(@PathVariable Long id){
        clientService.deleteClientById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client){
        return clientService.updateClient(id, client);
    }
}
