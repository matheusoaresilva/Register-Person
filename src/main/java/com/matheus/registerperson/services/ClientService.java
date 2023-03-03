package com.matheus.registerperson.services;

import com.matheus.registerperson.entities.Client;
import com.matheus.registerperson.repositories.ClientRepository;
import com.matheus.registerperson.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.apache.logging.log4j.Logger;


@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    private static final Logger logger = LogManager.getLogger(ClientService.class);

    @Transactional
    public Client findById(Long id){
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Client Not found with id: " + id));
    }

    @Transactional
    public Client insert(Client client){
        Client savedClient = repository.save(client);
        return savedClient;
    }

    @Transactional
    public Client update(Long id, Client client){
        Client existingClient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        existingClient.setName(client.getName());
        existingClient.setCpf(client.getCpf());
        existingClient.setIncome(client.getIncome());
        existingClient.setBirthDate(client.getBirthDate());
        existingClient.setChildren(client.getChildren());

        Client updatedClient = repository.save(existingClient);
        return updatedClient;
    }

    @Transactional
    public void delete(Long id){
        try{
            repository.deleteById(id);
            logger.info("Client with id {} successfully deleted", id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }

    }

    public Page<Client> findAllPage(PageRequest request){
        Page<Client> clients = repository.findAll(request);
        return clients;
    }
}
