package com.example.pokemon_api.service

import com.example.pokemon_api.dto.PokemonDto
import com.example.pokemon_api.model.Pokemon
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class PokemonService {
    private val pokemons: List<Pokemon>

    init {
        val resource = ClassPathResource("static/pokedex.json")
        val objectMapper = jacksonObjectMapper()

        val root = objectMapper.readValue<Map<String, List<PokemonDto>>>(
                InputStreamReader(resource.inputStream)
                )

        pokemons = root["pokemon"]?.map { it.toDomain() } ?: emptyList()
    }

    fun getAllPokemons(): List<Pokemon> = pokemons

    fun getPokemonById(id: Int): Pokemon? = pokemons.find { it.id == id }

}