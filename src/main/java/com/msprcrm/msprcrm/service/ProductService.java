package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    public void addProduct(Product product) {
        // Ajouter la logique pour ajouter un produit
        // Vous pouvez également effectuer des validations ici avant d'ajouter le produit

        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Méthode pour mettre à jour le stock d'un produit
    public Product updateStock(Long id, Integer newStock) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setStock(newStock);
                    return productRepository.save(existingProduct);
                })
                .orElse(null); // Gestion de l'absence du produit avec l'id spécifié
    }
    public long getTotalProduits() {
        List<Product> produits = productRepository.findAll();
        return produits.size();
    }


    public List<Product> getTopSellingProducts(int limit) {
        // Implémentation pour récupérer les produits les plus vendus
        // Utilisez la logique appropriée, par exemple, en fonction des quantités vendues
        // Retournez une liste de produits
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> Long.compare(p2.getTotalVentes(), p1.getTotalVentes()))
                .limit(limit)
                .collect(Collectors.toList());
    }


    // Vous pouvez ajouter d'autres méthodes CRUD personnalisées ici si nécessaire
}
