package io.reflectoring.gestionePrenotazioni.repository;

import io.reflectoring.gestionePrenotazioni.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByUsername(String username);
}
