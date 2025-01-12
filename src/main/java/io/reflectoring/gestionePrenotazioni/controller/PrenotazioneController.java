package io.reflectoring.gestionePrenotazioni.controller;


import io.reflectoring.gestionePrenotazioni.DTO.PrenotazioneCreateDTO;
import io.reflectoring.gestionePrenotazioni.model.Dipendente;
import io.reflectoring.gestionePrenotazioni.model.Prenotazione;
import io.reflectoring.gestionePrenotazioni.model.Viaggio;
import io.reflectoring.gestionePrenotazioni.service.PrenotazioneService;
import io.reflectoring.gestionePrenotazioni.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }
    @PostMapping("/viaggio/{viaggioId}")
    public ResponseEntity<Prenotazione> createPrenotazione(
            @PathVariable Long viaggioId,
            @RequestBody PrenotazioneCreateDTO prenotazioneCreateDTO) {
        try {

            Viaggio viaggio = viaggioService.getViaggioById(viaggioId);
            if (viaggio == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            if (!prenotazioneCreateDTO.getDataRichiesta().equals(viaggio.getData())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            Dipendente dipendente = new Dipendente();
            dipendente.setUsername(prenotazioneCreateDTO.getUsername());
            dipendente.setNome(prenotazioneCreateDTO.getNome());
            dipendente.setCognome(prenotazioneCreateDTO.getCognome());
            dipendente.setEmail(prenotazioneCreateDTO.getEmail());

            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setDipendente(dipendente);
            prenotazione.setViaggio(viaggio);
            prenotazione.setDataRichiesta(prenotazioneCreateDTO.getDataRichiesta());


            Prenotazione createdPrenotazione = prenotazioneService.savePrenotazione(prenotazione);

            return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


