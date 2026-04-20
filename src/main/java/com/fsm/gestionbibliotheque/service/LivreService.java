package com.fsm.gestionbibliotheque.service;

import com.fsm.gestionbibliotheque.model.Auteur;
import com.fsm.gestionbibliotheque.model.Livre;
import com.fsm.gestionbibliotheque.repository.AuteurRepository;
import com.fsm.gestionbibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre saveLivre(Livre livre) {
        // Ajouter des points aux auteurs [TP_2.pdf]
        if (livre.getAuteurs() != null) {
            for (Auteur auteur : livre.getAuteurs()) {
                auteur.setPoints(auteur.getPoints() + 10);
                auteurRepository.save(auteur);
            }
        }
        return livreRepository.save(livre);
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}