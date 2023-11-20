package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        if (commandeRepository.existsById(id)) {
            updatedCommande.setId(id);
            return commandeRepository.save(updatedCommande);
        }
        return null; // Gérer le cas où la commande n'existe pas
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}