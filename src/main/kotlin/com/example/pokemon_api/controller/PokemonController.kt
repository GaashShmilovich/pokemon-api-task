package com.example.pokemon_api.controller

import com.example.pokemon_api.model.Pokemon
import com.example.pokemon_api.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(private val pokemonService: PokemonService) {

    @GetMapping("api/pokemons")
    fun getAllPokemons(): List<Pokemon> = pokemonService.getAllPokemons()

    @GetMapping("api/pokemons/{id}")
    fun getPokemonById(@PathVariable("id") id: Int): Pokemon? =
        pokemonService.getPokemonById(id)
}