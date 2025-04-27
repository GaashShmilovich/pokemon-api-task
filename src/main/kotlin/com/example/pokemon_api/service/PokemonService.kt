package com.example.pokemon_api.service

import com.example.pokemon_api.model.Pokemon
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class PokemonService {

    fun getAllPokemons(): List<Pokemon> {
        // Load the JSON file from resources/static
        val resource = ClassPathResource("static/pokedex.json")
        val objectMapper = jacksonObjectMapper()

        val root = objectMapper.readValue<Map<String, List<Pokemon>>>(InputStreamReader(resource.inputStream))

        return root["pokemon"] ?: emptyList()
    }

    fun getPokemonById(id: Int): Pokemon? {
        return getAllPokemons().find { it.id == id }
    }
}