package com.example.pokemon_api.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.pokemon_api.model.Pokemon
import com.example.pokemon_api.model.PokemonType
import com.example.pokemon_api.service.PokemonService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.net.URI
import kotlin.text.get

@WebMvcTest(PokemonController::class)
class PokemonControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var pokemonService: PokemonService

    private val baseUrl = "http://example.com/img/"

    private val testPokemon1 = Pokemon(1, "Bulbasaur", listOf(PokemonType.GRASS), URI("${baseUrl}1.png"))
    private val testPokemon2 = Pokemon(2, "Ivysaur", listOf(PokemonType.GRASS), URI("${baseUrl}2.png"))

    @BeforeEach
    fun setUp() {
        whenever(pokemonService.getAllPokemons()).thenReturn(listOf(testPokemon1, testPokemon2))
        whenever(pokemonService.getPokemonById(1)).thenReturn(testPokemon1)
        whenever(pokemonService.getPokemonById(2)).thenReturn(testPokemon2)
        whenever(pokemonService.getPokemonById(999)).thenReturn(null)
    }

    @Test
    fun `should return all pokemons when GET pokemons is called`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Bulbasaur"))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[1].name").value("Ivysaur"))
            .andExpect(jsonPath("$[1].id").value(2))
    }

    @Test
    fun `should return pokemon by id when GET pokemons by id is called`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Bulbasaur"))
            .andExpect(jsonPath("$.id").value(1))
    }

    @Test
    fun `should return 404 when GET pokemons by id is called with invalid id`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons/999"))
            .andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").value("Pokemon with id 999 not found"))
    }
}