package io.reflectoring.gestionePrenotazioni.controller;

import io.reflectoring.gestionePrenotazioni.model.Viaggio;
import io.reflectoring.gestionePrenotazioni.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")


public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.getAllViaggi();
    }
    @PostMapping
    public Viaggio createViaggio(@RequestBody Viaggio viaggio) {
        return viaggioService.saveViaggio(viaggio);
    }
    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id){
        return viaggioService.getViaggioById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
    }
    @PutMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestParam String stato) {
        return viaggioService.updateStatoViaggio(id, stato);
    }
}
