package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire
}
