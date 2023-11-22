// com.msprcrm.msprcrm.service.StatistiquesService

package com.msprcrm.msprcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatistiquesService {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    public Map<String, Object> getStatistiques() {
        // Exemple de statistiques, ajustez en fonction de vos besoins
        long totalCommandes = commandeService.getTotalCommandes();
        long totalClients = clientService.getTotalClients();
        long totalProduits = productService.getTotalProduits();

        // Vous pouvez ajouter d'autres statistiques en fonction de vos besoins

        Map<String, Object> stats = Map.of(
                "totalCommandes", totalCommandes,
                "totalClients", totalClients,
                "totalProduits", totalProduits
        );

        return stats;
    }
}
