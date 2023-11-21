package com.msprcrm.msprcrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long ident;

    private String nom;

    private String type;

    private double prix;

    private String description;

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
}
