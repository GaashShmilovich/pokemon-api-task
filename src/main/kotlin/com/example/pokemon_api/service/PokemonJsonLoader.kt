package com.example.pokemon_api.service

import com.example.pokemon_api.dto.PokemonDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class PokemonJsonLoader {
    fun loadFrom(inputStream: InputStream): List<PokemonDto> {
        val objectMapper = jacksonObjectMapper()
        val root = objectMapper.readValue<Map<String, List<PokemonDto>>>(inputStream)
        return root["pokemon"] ?: emptyList()
    }

    fun loadFromResource(): List<PokemonDto> {
        val resource = ClassPathResource("static/pokedex.json")
        return loadFrom(resource.inputStream)
    }
}