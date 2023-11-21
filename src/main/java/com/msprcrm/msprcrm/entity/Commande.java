package com.msprcrm.msprcrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
@Entity
public class Commande {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        private Long id;

        @OneToOne
        private Produit produit;

        @OneToOne
        private Client client;

        private Date date_achat;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Produit getProduit() {
            return produit;
        }

        public void setProduit(Produit produit) {
            this.produit = produit;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public Date getDate_achat() {
            return date_achat;
        }

        public void setDate_achat(Date date_achat) {
            this.date_achat = date_achat;
        }

}
