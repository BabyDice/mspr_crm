package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.payload.CreateClientRequest;
import com.msprcrm.msprcrm.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("getClients")
    public ResponseEntity<?> getClient(){
        try {
            List<Client> clientList = clientRepository.findAll();
            return ResponseEntity.ok().body(clientList);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("createClient")
    public ResponseEntity<?> createClient(@RequestBody CreateClientRequest createClientRequest){
        try {

            Client client = new Client();
            client.setNom(createClientRequest.getNom());
            client.setPrenom(createClientRequest.getPrenom());
            client.setAdresse(createClientRequest.getAdresse());
            client.setCode_postal(createClientRequest.getCode_postal());
            client.setVille(createClientRequest.getVille());
            client.setDate_naissance(createClientRequest.getDate_naissance());
            client.setTelephone(createClientRequest.getTelephone());
            client.setEmail(createClientRequest.getEmail());

            clientRepository.save(client);
            return ResponseEntity.ok().body(client);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("editClients")
    public ResponseEntity<?> editClients(@RequestBody CreateClientRequest createClientRequest){
        try {
            Client client = new Client();
            client.setNom(createClientRequest.getNom());
            client.setPrenom(createClientRequest.getPrenom());
            client.setAdresse(createClientRequest.getAdresse());
            client.setCode_postal(createClientRequest.getCode_postal());
            client.setVille(createClientRequest.getVille());
            client.setDate_naissance(createClientRequest.getDate_naissance());
            client.setTelephone(createClientRequest.getTelephone());
            client.setEmail(createClientRequest.getEmail());

            clientRepository.save(client);
            return ResponseEntity.ok().body(client);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("client/{ident}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "ident") Long clientId){
        try {

            Client client = clientRepository.getClientByIdent(clientId).get();
            clientRepository.delete(client);
            return ResponseEntity.ok().body("Client deleted");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
