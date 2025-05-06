package com.example.pokemon_api.service

import com.example.pokemon_api.model.Pokemon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PokemonService(private val pokemonJsonLoader: PokemonJsonLoader) {
    private val pokemons: List<Pokemon> by lazy {
        pokemonJsonLoader.loadFromResource().map { it.toDomain() }
    }

    fun getAllPokemons(): List<Pokemon> = pokemons
    fun getPokemonById(id: Int): Pokemon? = pokemons.find { it.id == id }
}