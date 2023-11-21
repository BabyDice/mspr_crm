package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Client ajouté avec succès.");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok("Client supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du client.");
        }
    }
}
