package com.msprcrm.msprcrm.service;

import com.msprcrm.msprcrm.entity.Statistiques;
import com.msprcrm.msprcrm.repository.StatistiquesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatistiquesService {

    @Autowired
    private StatistiquesRepository statistiqueRepository;

    public List<Statistiques> getAllStatistiques() {
        return statistiqueRepository.findAll();
    }

    public Optional<Statistiques> getStatistiqueById(Long id) {
        return statistiqueRepository.findById(id);
    }
}
