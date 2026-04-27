package com.fsm.gestionbibliotheque.controller;

import com.fsm.gestionbibliotheque.model.Auteur;
import com.fsm.gestionbibliotheque.model.Livre;
import com.fsm.gestionbibliotheque.service.AuteurService;
import com.fsm.gestionbibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class AffectationController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AuteurService auteurService;

    @GetMapping("/affecter")
    public String showAffectation(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "livres/affecter";
    }

    @PostMapping("/affecter")
    public String affecter(
            @RequestParam(required = false) Long livreId,
            @RequestParam(required = false) Long auteurId) {

        if (livreId == null || auteurId == null) {
            return "redirect:/affecter";
        }

        Livre livre = livreService.getLivreById(livreId);
        Auteur auteur = auteurService.getAuteurById(auteurId);

        if (livre != null && auteur != null) {

            if (livre.getAuteurs() == null) {
                livre.setAuteurs(new ArrayList<>());
            }

            if (!livre.getAuteurs().contains(auteur)) {
                livre.getAuteurs().add(auteur);
                auteur.setPoints(auteur.getPoints() + 10);
                auteurService.saveAuteur(auteur);
                livreService.saveLivre(livre);
            }
        }

        return "redirect:/affecter";
    }
}