// com.msprcrm.msprcrm.service.StatistiquesServiceInt

package com.msprcrm.msprcrm.service;
import com.msprcrm.msprcrm.entity.Product;

import com.msprcrm.msprcrm.entity.ProduitStatistique;

import java.util.List;
import java.util.Map;

    public interface StatistiquesServiceInt {
        Map<String, Object> getStatistiques();
        long getTotalVentes();
        long getTotalVentesParClient(Long clientId);
        List<ProduitStatistique> getProduitsPlusVendus(int limit);
        List<ProduitStatistique> getProduitsMoinsVendus(int limit);
        List<Product> getTopSellingProducts(int limit);  // Assurez-vous
    }
