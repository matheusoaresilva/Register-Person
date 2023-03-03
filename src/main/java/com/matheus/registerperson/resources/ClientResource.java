package com.matheus.registerperson.resources;

import com.matheus.registerperson.entities.Client;
import com.matheus.registerperson.services.ClientService;
import com.matheus.registerperson.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id){
        try {
            Client client = service.findById(id);
            return ResponseEntity.ok(client);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestParam Client client){
        if (client.getId() == null){
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (service.findById(client.getId()) != null){
            throw new IllegalArgumentException("Id already exists: " + client.getId());
        }
        if (client.getName() == null){
            throw new IllegalArgumentException("Name cannot be null!");
        }if (client.getCpf() == null){
            throw new IllegalArgumentException("CPF cannot be null!");
        }if (service.findByCpf(client.getCpf())!= null){
            throw new IllegalArgumentException("CPF already exists: " + client.getCpf());
        }if (client.getIncome() == null){
            throw new IllegalArgumentException("Income cannot be null!");
        }if (client.getBirthDate() == null){
            throw new IllegalArgumentException("BirthDate cannot be null!");
        }if (client.getChildren() == null){
            throw new IllegalArgumentException("Children cannot be null!");
        }
        Client insertedClient = service.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(insertedClient.getId()).toUri();
        return ResponseEntity.created(uri).body(insertedClient);
    }
}
