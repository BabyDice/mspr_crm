// CommandeService.java
package com.msprcrm.msprcrm.service;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

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


    public List<Commande> getCommandesByMonthAndYear(int month, int year) {
        // Create a LocalDate to represent the first day of the specified month
        LocalDate startDate = LocalDate.of(year, month, 1);

        // Create a LocalDate to represent the last day of the specified month
        LocalDate endDate = LocalDate.of(year, month, Month.of(month).length(false));

        // Convert LocalDate to Date (if needed)
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        // Use the repository to retrieve commands between startDate and endDate
        return commandeRepository.findByDateCommandeBetween(sqlStartDate, sqlEndDate);
    }

    public List<Commande> getCommandesByYear(int year) {
        // Create a LocalDate to represent the first day of the specified year
        LocalDate startDate = LocalDate.of(year, 1, 1);

        // Create a LocalDate to represent the last day of the specified year
        LocalDate endDate = LocalDate.of(year, 12, 31);

        // Convert LocalDate to Date (if needed)
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        // Use the repository to retrieve commands between startDate and endDate
        return commandeRepository.findByDateCommandeBetween(sqlStartDate, sqlEndDate);
    }
}

