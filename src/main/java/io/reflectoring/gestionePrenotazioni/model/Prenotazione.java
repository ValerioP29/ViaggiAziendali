package io.reflectoring.gestionePrenotazioni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Prenotazione {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @NotNull
    private Dipendente dipendente;

    @ManyToOne
    @NotNull
    private Viaggio viaggio;

    @NotNull
    @FutureOrPresent
    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;

    private String note;

    public Dipendente getDipendente() {
        return dipendente;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public String getNote() {
        return note;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }
}
