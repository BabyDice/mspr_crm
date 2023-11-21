package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
