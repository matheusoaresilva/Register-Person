package com.matheus.registerperson.resources;

import com.matheus.registerperson.entities.Client;
import com.matheus.registerperson.services.ClientService;
import com.matheus.registerperson.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
