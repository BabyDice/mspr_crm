package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Product;
import com.msprcrm.msprcrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Produit ajouté avec succès.");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return ResponseEntity.ok(productService.saveProduct(existingProduct));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Produit supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du produit.");
        }
    }

    @PutMapping("/{id}/updateStock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestParam Integer newStock) {
        Product updatedProduct = productService.updateStock(id, newStock);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
