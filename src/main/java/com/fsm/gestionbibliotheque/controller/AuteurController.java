package com.fsm.gestionbibliotheque.controller;

import com.fsm.gestionbibliotheque.model.Auteur;
import com.fsm.gestionbibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    public String getAllAuteurs(Model model) {
        model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "auteurs/liste";
    }

    @GetMapping("/ajouter")
    public String showForm(Model model) {
        model.addAttribute("auteur", new Auteur());
        return "auteurs/form";
    }

    @PostMapping("/ajouter")
    public String saveAuteur(@ModelAttribute Auteur auteur) {
        auteurService.saveAuteur(auteur);
        return "redirect:/auteurs";
    }

    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("auteur", auteurService.getAuteurById(id));
        return "auteurs/modifier";
    }

    @PostMapping("/modifier/{id}")
    public String updateAuteur(@PathVariable Long id, @ModelAttribute Auteur auteur) {
        auteur.setId(id);
        auteurService.saveAuteur(auteur);
        return "redirect:/auteurs";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteAuteur(@PathVariable Long id) {
        auteurService.deleteAuteur(id);
        return "redirect:/auteurs";
    }
}