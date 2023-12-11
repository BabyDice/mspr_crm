package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire
    List<Commande> findByDateCommandeBetween(Date startDate, Date endDate);
}
