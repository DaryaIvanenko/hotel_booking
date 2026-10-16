package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Client;
import org.example.entity.UserRole;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final ClientService clientService;

    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public Client register(@Valid @RequestBody Client client){
        client.setRole(UserRole.ROLE_USER);
        return clientService.saveClient(client);
    }
}
