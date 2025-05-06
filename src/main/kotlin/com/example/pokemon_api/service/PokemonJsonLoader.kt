package com.example.pokemon_api.service

import com.example.pokemon_api.dto.PokemonDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class PokemonJsonLoader {
    private val objectMapper = jacksonObjectMapper()
    private var cachedData: List<PokemonDto>? = null

    @Cacheable("pokemons")
    fun loadFromResource(): List<PokemonDto> {
        if (cachedData == null) {
            val resource = ClassPathResource("static/pokedex.json")
            cachedData = objectMapper.readValue(resource.inputStream)
        }
        return cachedData ?: emptyList()
    }
}