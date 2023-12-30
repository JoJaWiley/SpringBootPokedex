package com.example.pokemondb.catalog.persistence;

import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Integer> {
    PokemonEntity findByName(String name);
}
