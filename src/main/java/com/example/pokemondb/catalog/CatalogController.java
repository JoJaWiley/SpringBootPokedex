package com.example.pokemondb.catalog;

import org.springframework.web.bind.annotation.GetMapping;

public class CatalogController {

    @GetMapping("/")
    String index() {
        return "index";
    }
}
