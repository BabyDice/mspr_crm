package com.msprcrm.msprcrm.repository;

import com.msprcrm.msprcrm.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> getProduitByIdent(Long ident);
}
