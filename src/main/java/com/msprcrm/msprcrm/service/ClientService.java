package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = getClientById(id);

        if (existingClient != null) {
            // Mettez à jour les propriétés nécessaires
            existingClient.setName(updatedClient.getName());
            existingClient.setLastName(updatedClient.getLastName());
            existingClient.setCoordonnees(updatedClient.getCoordonnees());
            existingClient.setEmail(updatedClient.getEmail());

            return clientRepository.save(existingClient);
        }

        return null;
    }

    public long getTotalClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.size();
    }


    public void addClient(Client client) {
        // Ajouter la logique pour ajouter un produit
        // Vous pouvez également effectuer des validations ici avant d'ajouter le produit

        clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
