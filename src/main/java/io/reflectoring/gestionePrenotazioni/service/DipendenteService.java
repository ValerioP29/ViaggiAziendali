package io.reflectoring.gestionePrenotazioni.service;

import io.reflectoring.gestionePrenotazioni.model.Dipendente;
import io.reflectoring.gestionePrenotazioni.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }
    public Dipendente saveDipendente( Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new IllegalArgumentException("Username già in uso!!");
        }
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente getDipendenteById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Dipendente non trovato!!"));
    }
    public void deleteDipendente(Long id) {
         dipendenteRepository.deleteById(id);
    }
}
