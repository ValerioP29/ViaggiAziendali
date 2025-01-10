package io.reflectoring.gestionePrenotazioni.service;

import io.reflectoring.gestionePrenotazioni.model.StatoViaggio;
import io.reflectoring.gestionePrenotazioni.model.Viaggio;
import io.reflectoring.gestionePrenotazioni.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }
    public Viaggio saveViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }
    public Viaggio getViaggioById(Long id) {
        return viaggioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Viaggio non trovato!!"));
    }
    public void deleteViaggio(Long id) {
        viaggioRepository.deleteById(id);
    }
    public Viaggio updateStatoViaggio(Long id, String stato) {
        Viaggio viaggio = getViaggioById(id);
        viaggio.setStato(StatoViaggio.valueOf(stato));
        return viaggioRepository.save(viaggio);
    }
}
