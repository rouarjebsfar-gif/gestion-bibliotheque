package com.fsm.gestionbibliotheque.repository;

import com.fsm.gestionbibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
}