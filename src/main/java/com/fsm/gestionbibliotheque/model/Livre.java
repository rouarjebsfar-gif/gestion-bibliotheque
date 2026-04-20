package com.fsm.gestionbibliotheque.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String isbn;

    @ManyToMany
    @JoinTable(
            name = "livre_auteur",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "auteur_id")
    )
    private List<Auteur> auteurs;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public List<Auteur> getAuteurs() { return auteurs; }
    public void setAuteurs(List<Auteur> auteurs) { this.auteurs = auteurs; }
}