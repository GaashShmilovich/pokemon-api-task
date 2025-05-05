package com.example.pokemon_api.service

import com.example.pokemon_api.dto.PokemonDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.InputStream
import kotlin.io.inputStream
import kotlin.text.get

@Component
class PokemonJsonLoader {
    private val objectMapper = jacksonObjectMapper()

    @Cacheable("pokemons")
    fun loadFrom(inputStream: InputStream): List<PokemonDto> {
        return inputStream.use { stream ->
            val root = objectMapper.readValue<Map<String, List<PokemonDto>>>(stream)
            root["pokemon"] ?: emptyList()
        }
    }

    @Cacheable("pokemons")
    fun loadFromResource(): List<PokemonDto> {
        return ClassPathResource("static/pokedex.json")
            .inputStream
            .let { loadFrom(it) }
    }
}