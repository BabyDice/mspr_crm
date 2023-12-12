package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.service.ClientService;
import com.msprcrm.msprcrm.service.CommandeService;
import com.msprcrm.msprcrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommandeController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CommandeService commandeService;
    @GetMapping("/getAllCommandes")
    @ResponseBody
    public List<Commande> getAllCommandes(Model model) {
        List<Commande> commandes = commandeService.getAllCommandes();
        return commandes;
    }

    @PostMapping("/add")
    public ResponseEntity<List<Commande>> addCommande(@RequestBody Commande commande) {
        try {
            commandeService.addCommande(commande);
            List<Commande> updatedCommandes = commandeService.getAllCommandes();
            return ResponseEntity.ok().body(updatedCommandes);
        } catch (Exception e) {
            e.printStackTrace();
            // Ajoutez un log détaillé de l'exception
            System.err.println("Exception during addCommande: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
