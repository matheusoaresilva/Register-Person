package com.matheus.registerperson.services;

import com.matheus.registerperson.entities.Client;
import com.matheus.registerperson.repositories.ClientRepository;
import com.matheus.registerperson.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Client findById(Long id){
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Client Not found with id: " + id));
    }

    public Client insert(Client client){
        Client savedClient = repository.save(client);
        return savedClient;
    }
}
