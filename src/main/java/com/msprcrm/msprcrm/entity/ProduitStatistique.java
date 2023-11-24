package com.msprcrm.msprcrm.entity;// ProduitStatistique.java

// ProduitStatistique.java

public class ProduitStatistique {
    private Long productId;
    private String nomProduit;
    private long totalVentes;

    // Constructeur avec les arguments
    public ProduitStatistique(Long productId, String nomProduit, long totalVentes) {
        this.productId = productId;
        this.nomProduit = nomProduit;
        this.totalVentes = totalVentes;
    }

    // Getters et setters
    // ...

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public long getTotalVentes() {
        return totalVentes;
    }

    public void setTotalVentes(long totalVentes) {
        this.totalVentes = totalVentes;
    }
}

