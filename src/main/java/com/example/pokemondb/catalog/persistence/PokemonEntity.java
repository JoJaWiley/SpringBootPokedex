package com.example.pokemondb.catalog.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    int id;

    @NotBlank
    String name;

    @NotBlank
    String type;
}
