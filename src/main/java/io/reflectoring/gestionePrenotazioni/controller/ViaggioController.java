// Updated ViaggioController
package io.reflectoring.gestionePrenotazioni.controller;

import io.reflectoring.gestionePrenotazioni.model.Viaggio;
import io.reflectoring.gestionePrenotazioni.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<Viaggio>> getAllViaggi() {
        List<Viaggio> viaggi = viaggioService.getAllViaggi();
        return new ResponseEntity<>(viaggi, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        Viaggio createdViaggio = viaggioService.saveViaggio(viaggio);
        return new ResponseEntity<>(createdViaggio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioById(@PathVariable Long id) {
        Viaggio viaggio = viaggioService.getViaggioById(id);
        if (viaggio != null) {
            return new ResponseEntity<>(viaggio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        Viaggio viaggio = viaggioService.getViaggioById(id);
        if (viaggio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        viaggioService.deleteViaggio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable Long id, @RequestParam String stato) {
        Viaggio viaggio = viaggioService.getViaggioById(id);
        if (viaggio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Viaggio updatedViaggio = viaggioService.updateStatoViaggio(id, stato);
        return new ResponseEntity<>(updatedViaggio, HttpStatus.OK);
    }
}
