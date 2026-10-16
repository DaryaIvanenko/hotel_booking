package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Client;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиента с id " + id + " не найдено"));
    }

    public Client saveClient(Client client){
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        return clientRepository.save(client);
    }

    public void deleteAllClients(){
        clientRepository.deleteAll();
    }

    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }

    public Client updateClient(Long id, Client updateClient){
       Client client = getClientById(id);
       client.setEmail(updateClient.getEmail());
       client.setName(updateClient.getName());
       client.setPhoneNumber(updateClient.getPhoneNumber());
       String encodedPassword = passwordEncoder.encode(updateClient.getPassword());
       client.setPassword(encodedPassword);
       client.setRole(updateClient.getRole());
       return clientRepository.save(client);
    }

}
