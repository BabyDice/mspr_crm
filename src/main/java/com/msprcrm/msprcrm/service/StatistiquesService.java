package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Commande;
import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.entity.ProduitStatistique;
import com.msprcrm.msprcrm.service.ClientService;
import com.msprcrm.msprcrm.service.CommandeService;
import com.msprcrm.msprcrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatistiquesService implements StatistiquesServiceInt {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    // ... autres injections si nécessaires

    @Override
    public Map<String, Object> getStatistiques() {
        // Implémentation de la récupération des statistiques
        // ...

        return null; // Remplacez par les statistiques réelles
    }

    @Override
    public long getTotalVentes() {
        // Implémentation du calcul du total des ventes
        // ...

        return 0; // Remplacez par le total réel des ventes
    }

    @Override
    public long getTotalVentesParClient(Long clientId) {
        // Implémentation du calcul du total des ventes par client
        // ...

        return 0; // Remplacez par le total réel des ventes par client
    }

        // ... Autres injections si nécessaires

    @Override
    public List<ProduitStatistique> getProduitsPlusVendus(int limit) {
        // Récupérer la liste des commandes
        List<Commande> commandes = commandeService.getAllCommandes();

        // Compter le nombre d'occurrences de chaque produit dans la liste des commandes
        Map<Long, Long> ventesProduitMap = commandes.stream()
                .map(commande -> commande.getProduct().getId())
                .collect(Collectors.groupingBy(
                        productId -> productId,
                        Collectors.counting()
                ));

        // Obtenir les produits les plus vendus en fonction du nombre de ventes
        List<ProduitStatistique> produitsPlusVendus = ventesProduitMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> {
                    Long productId = entry.getKey();
                    Long venteCount = entry.getValue();

                    Product produit = productService.getProductById(productId).orElse(null);

                    return new ProduitStatistique(
                            productId,
                            produit != null ? produit.getNom() : "",
                            venteCount
                    );
                })
                .collect(Collectors.toList());

        // Obtenir le produit le moins vendu
        Optional<Map.Entry<Long, Long>> produitMoinsVenduEntry = ventesProduitMap.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        ProduitStatistique produitMoinsVendu = produitMoinsVenduEntry.map(entry -> {
            Long productId = entry.getKey();
            Long venteCount = entry.getValue();
            Product produit = productService.getProductById(productId).orElse(null);

            return new ProduitStatistique(
                    productId,
                    produit != null ? produit.getNom() : "",
                    venteCount
            );
        }).orElse(null);

        // Vous pouvez maintenant utiliser produitsPlusVendus et produitMoinsVendu comme nécessaire.

        return produitsPlusVendus;
    }


    @Override
    public List<ProduitStatistique> getProduitsMoinsVendus(int limit) {
        // Implémentation de la récupération des produits les moins vendus
        // ...

        return null; // Remplacez par les produits réels les moins vendus
    }

    @Override
    public List<Product> getTopSellingProducts(int limit) {
        // Implémentation pour récupérer les produits les plus vendus
        // Replace this with your actual logic to get top-selling products
        return productService.getTopSellingProducts(limit);
    }
}
