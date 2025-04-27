package com.example.pokemon_api.service

import com.example.pokemon_api.model.Pokemon
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class PokemonService {

    fun getAllPokemons(): List<Pokemon> {
        val resource = ClassPathResource("static/pokedex.json")
        val objectMapper = jacksonObjectMapper()

        val pokemons = objectMapper.readValue(InputStreamReader(resource.inputStream), Array<Pokemon>::class.java)
        return pokemons.toList()
    }

    fun getPokemonById(id: Int): Pokemon? {
        return getAllPokemons().find { it.id == id }
    }
}