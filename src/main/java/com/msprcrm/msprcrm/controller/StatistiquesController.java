package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Statistiques;
import com.msprcrm.msprcrm.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistiques")
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiqueService;

    @GetMapping
    public List<Statistiques> getAllStatistiques() {
        return statistiqueService.getAllStatistiques();
    }

    @GetMapping("/{id}")
    public Optional<Statistiques> getStatistiqueById(@PathVariable Long id) {
        return statistiqueService.getStatistiqueById(id);
    }
}
