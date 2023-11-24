// com.msprcrm.msprcrm.controller.StatistiquesController

package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.entity.ProduitStatistique;
import com.msprcrm.msprcrm.entity.Statistiques;
import com.msprcrm.msprcrm.service.ClientService;
import com.msprcrm.msprcrm.service.CommandeService;
import com.msprcrm.msprcrm.service.ProductService;
import com.msprcrm.msprcrm.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// StatistiquesController.java

@RestController
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;

    @GetMapping("/stats")
    public ResponseEntity<Statistiques> getStatistiques() {
        Statistiques statistiquesDTO = new Statistiques();
        statistiquesDTO.setTotalVentes(statistiquesService.getTotalVentes());
        // Ajoutez d'autres statistiques nécessaires

        return ResponseEntity.ok(statistiquesDTO);
    }

    @GetMapping("/produitsPlusVendus")
    public ResponseEntity<List<ProduitStatistique>> getProduitsPlusVendus(@RequestParam int limit) {
        List<ProduitStatistique> produitsPlusVendus = statistiquesService.getProduitsPlusVendus(limit);
        return ResponseEntity.ok(produitsPlusVendus);
    }

    // Ajoutez d'autres méthodes pour les statistiques nécessaires
}

