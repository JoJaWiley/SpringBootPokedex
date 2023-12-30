package com.example.pokemondb.catalog.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;


@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String name;

    @NotBlank
    String type;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PokemonEntity) {
            PokemonEntity other = (PokemonEntity) obj;
            return Objects.equals(this.id, other.id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
