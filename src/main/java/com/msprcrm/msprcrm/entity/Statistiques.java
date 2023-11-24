package com.msprcrm.msprcrm.entity;

import jakarta.persistence.*;

@Entity
public class Statistiques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long totalVentes;
    // Ajoutez les champs nécessaires pour représenter les statistiques

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTotalVentes() {
        return totalVentes;
    }

    public void setTotalVentes(long totalVentes) {
        this.totalVentes = totalVentes;
    }
}
