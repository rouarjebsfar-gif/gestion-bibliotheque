package com.fsm.gestionbibliotheque.controller;

import com.fsm.gestionbibliotheque.model.Livre;
import com.fsm.gestionbibliotheque.service.LivreService;
import com.fsm.gestionbibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    public String getAllLivres(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        return "livres/liste";
    }

    @GetMapping("/ajouter")
    public String showForm(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "livres/form";
    }

    @PostMapping("/ajouter")
    public String saveLivre(@ModelAttribute Livre livre) {
        livreService.saveLivre(livre);
        return "redirect:/livres";
    }

    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("livre", livreService.getLivreById(id));
        model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "livres/modifier";
    }

    @PostMapping("/modifier/{id}")
    public String updateLivre(@PathVariable Long id, @ModelAttribute Livre livre) {
        livre.setId(id);
        livreService.saveLivre(livre);
        return "redirect:/livres";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return "redirect:/livres";
    }
}