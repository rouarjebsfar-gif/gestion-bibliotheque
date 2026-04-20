package com.fsm.gestionbibliotheque.service;

import com.fsm.gestionbibliotheque.model.Auteur;
import com.fsm.gestionbibliotheque.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    public Auteur saveAuteur(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public Auteur getAuteurById(Long id) {
        return auteurRepository.findById(id).orElse(null);
    }

    public void deleteAuteur(Long id) {
        auteurRepository.deleteById(id);
    }
}