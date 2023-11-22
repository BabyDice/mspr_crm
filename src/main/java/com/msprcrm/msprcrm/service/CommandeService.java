// CommandeService.java
package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }
    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public long getTotalCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.size();
    }

}
