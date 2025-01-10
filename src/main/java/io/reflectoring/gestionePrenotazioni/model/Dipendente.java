package io.reflectoring.gestionePrenotazioni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @Email
    @NotBlank
    private String email;

    @Lob // Per il salvataggio dell'immagine
    private byte[] immagineProfilo;  // Questo è il campo per l'immagine

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Questo metodo serve a impostare il percorso dell'immagine come stringa nel database
    public void setImmagineProfiloFromPath(String filePath) {
        this.immagineProfilo = filePath.getBytes();  // Memorizza il percorso come byte[]
    }
}
