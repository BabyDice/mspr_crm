package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.service.ClientService;
import com.msprcrm.msprcrm.service.CommandeService;
import com.msprcrm.msprcrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/commandes")
    @ResponseBody
    public List<Commande> getAllCommandes(Model model) {
        List<Commande> commandes = commandeService.getAllCommandes();
        return commandes;
    }

    @PostMapping("/add")
    public String addCommande(@ModelAttribute("commande") Commande commande) {
        // Logique pour ajouter la commande
        // Après l'ajout, redirigez vers la page des commandes
        return "redirect:/commandes";
    }

    // Autres méthodes du contrôleur...
}
