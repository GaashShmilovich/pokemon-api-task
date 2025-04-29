package com.example.pokemon_api.controller

import com.example.pokemon_api.model.Pokemon
import com.example.pokemon_api.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class PokemonController(private val pokemonService: PokemonService) {

    @GetMapping("api/pokemons")
    fun getAllPokemons(): List<Pokemon> = pokemonService.getAllPokemons()

    @GetMapping("api/pokemons/{id}")
    fun getPokemonById(@PathVariable id: Int): Pokemon =
        pokemonService.getPokemonById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon with id $id not found")
}