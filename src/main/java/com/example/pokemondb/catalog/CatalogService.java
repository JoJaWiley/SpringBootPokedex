package com.example.pokemondb.catalog;

public interface CatalogService {

    Iterable<Pokemon> getAllPokemon();

    Pokemon getPokemonByName(String name);
}
