package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Client;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиента с id " + id + " не найдено"));
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteAllClients(){
        clientRepository.deleteAll();
    }

    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }
}
