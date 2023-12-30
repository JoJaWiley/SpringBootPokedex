package com.example.pokemondb.catalog.persistence;

import com.example.pokemondb.catalog.CatalogService;
import com.example.pokemondb.catalog.Pokemon;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaCatalogService implements CatalogService {

    private final PokemonRepository pokemonRepository;

    JpaCatalogService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Iterable<Pokemon> getAllPokemon() {
        return StreamSupport.stream(pokemonRepository.findAll().spliterator(), false) //static stream factory method
                .map(this::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        PokemonEntity entity = this.pokemonRepository.findByName(name);
        if (entity == null) {
            return null;
        }

        return mapEntity(entity);
    }

    Pokemon mapEntity(PokemonEntity entity) {
        return new Pokemon(entity.id, entity.name, entity.type);
    }
}
