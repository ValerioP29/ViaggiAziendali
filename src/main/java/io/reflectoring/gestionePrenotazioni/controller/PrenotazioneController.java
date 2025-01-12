package io.reflectoring.gestionePrenotazioni.controller;

import io.reflectoring.gestionePrenotazioni.model.Prenotazione;
import io.reflectoring.gestionePrenotazioni.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(@RequestBody Prenotazione prenotazione) {
        Prenotazione createdPrenotazione = prenotazioneService.savePrenotazione(prenotazione);
        return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
    }
}


