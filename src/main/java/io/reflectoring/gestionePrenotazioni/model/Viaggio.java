package io.reflectoring.gestionePrenotazioni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

@Entity

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destinazione;

    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @NotNull
    @Enumerated (EnumType.STRING)
    private StatoViaggio stato;




    public String getDestinazione() {
        return destinazione;
    }

    public LocalDate getData() {
        return data;
    }

    public StatoViaggio getStato() {
        return stato;
    }

    public Long getViaggioId() {
        return id;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setStato(StatoViaggio stato) {
        this.stato = stato;
    }
}
