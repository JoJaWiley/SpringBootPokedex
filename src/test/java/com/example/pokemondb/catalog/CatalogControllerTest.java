package com.example.pokemondb.catalog;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CatalogController.class)
public class CatalogControllerTest {

    private WebClient webClient;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CatalogService catalogService;

    @BeforeEach
    void setUp() {
        this.webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).build();
    }

    @Test
    @DisplayName("index page returns landing page")
    void returnsLandingPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Pokemon")));
    }

    @Test
    @DisplayName("index page returns a list of Pokemon from the db")
    void returnsListOfPokemonFromDb() throws Exception {
        final String expectedName = "Pikachu";
        mockPokemon(expectedName, "Electric");

        HtmlPage page = webClient.getPage("http://localhost/");

        assertThat(page.querySelectorAll(".card-title"))
                .anyMatch((domElement -> expectedName.equals(domElement.asNormalizedText())));
    }

    private void mockPokemon(String name, String type) {
        when(catalogService.getAllPokemon()).thenReturn(Collections.singletonList(new Pokemon(0, name, type)));
    }
}
