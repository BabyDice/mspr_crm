package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Statistiques;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiquesRepository extends JpaRepository<Statistiques, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
