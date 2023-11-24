package com.msprcrm.msprcrm.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private long totalVentes;


    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName(); // ou toute autre logique pour afficher le nom du client
    }


    public String getNom() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public long getTotalVentes() {
        return totalVentes;
    }

    public void setTotalVentes(long totalVentes) {
        this.totalVentes = totalVentes;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    // Constructeurs, getters, setters


}
