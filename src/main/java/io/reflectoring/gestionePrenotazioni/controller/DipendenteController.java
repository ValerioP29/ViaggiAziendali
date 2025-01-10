package io.reflectoring.gestionePrenotazioni.controller;

import io.reflectoring.gestionePrenotazioni.DTO.DipendenteCreateDTO;
import io.reflectoring.gestionePrenotazioni.model.Dipendente;
import io.reflectoring.gestionePrenotazioni.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    private static final String UPLOAD_DIR = "uploads/";  // Usa un percorso relativo per evitare path hardcoded

    // Restituisce tutti i dipendenti
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    // Crea un nuovo dipendente
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody DipendenteCreateDTO dipendenteCreateDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteCreateDTO.getUsername());
        dipendente.setNome(dipendenteCreateDTO.getNome());
        dipendente.setCognome(dipendenteCreateDTO.getCognome());
        dipendente.setEmail(dipendenteCreateDTO.getEmail());

        Dipendente createdDipendente = dipendenteService.saveDipendente(dipendente);
        return new ResponseEntity<>(createdDipendente, HttpStatus.CREATED);
    }


    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadImmagine(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Dipendente dipendente = dipendenteService.getDipendenteById(id);
            if (dipendente == null) {
                return new ResponseEntity<>("Dipendente non trovato", HttpStatus.NOT_FOUND);
            }

            // Verifica se il file è vuoto
            if (file.isEmpty()) {
                return new ResponseEntity<>("File vuoto", HttpStatus.BAD_REQUEST);
            }

            // Crea il percorso del file
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());  // Crea la directory se non esiste

            // Salva il file nel file system
            Files.write(path, file.getBytes());

            // Memorizza il percorso del file nel dipendente
            dipendente.setImmagineProfiloFromPath(filePath);
            dipendenteService.saveDipendente(dipendente);

            return new ResponseEntity<>("Immagine caricata con successo!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Errore durante il caricamento dell'immagine", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Restituisce i dettagli di un dipendente dato l'id
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id) {
        Dipendente dipendente = dipendenteService.getDipendenteById(id);
        if (dipendente != null) {
            return new ResponseEntity<>(dipendente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Elimina un dipendente dato l'id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        if (dipendenteService.getDipendenteById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dipendenteService.deleteDipendente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
