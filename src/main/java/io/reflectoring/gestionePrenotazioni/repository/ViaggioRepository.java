package io.reflectoring.gestionePrenotazioni.repository;

import io.reflectoring.gestionePrenotazioni.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
