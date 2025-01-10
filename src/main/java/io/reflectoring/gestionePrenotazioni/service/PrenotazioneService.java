package io.reflectoring.gestionePrenotazioni.service;

import io.reflectoring.gestionePrenotazioni.model.Prenotazione;
import io.reflectoring.gestionePrenotazioni.repository.PrenotazioneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }
    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        if (prenotazioneRepository.existsByIdAndDataRichiesta(prenotazione.getDipendente().getId(), prenotazione.getDataRichiesta())) {
            throw new IllegalArgumentException("Prenotazione non possibile in questa data!!");
        }
        return prenotazioneRepository.save(prenotazione);
    }
}
