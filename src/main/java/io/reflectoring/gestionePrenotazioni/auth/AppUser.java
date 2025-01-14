package io.reflectoring.gestionePrenotazioni.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "users")

public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private String username;

    @Setter
    @Getter
    @Column(nullable = false)
    private String password;

    @Setter
    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public AppUser(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}