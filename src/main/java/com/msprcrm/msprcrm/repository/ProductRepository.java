package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
