package io.reflectoring.gestionePrenotazioni.repository;

import io.reflectoring.gestionePrenotazioni.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository <Prenotazione, Long>{
    boolean existsByIdAndDataRichiesta(Long id, LocalDate dataRichiesta);
}
