package com.example.pokemon_api.dto

import com.example.pokemon_api.model.Pokemon
import com.example.pokemon_api.model.PokemonType
import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.net.URL

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id", "name", "type", "img")
data class PokemonDto(
    @JsonProperty("num") private val num: String,
    val name: String,
    @JsonAlias("type")
    val types: List<PokemonType>,
    val img: URL
) {
    fun toDomain(): Pokemon = Pokemon(
        id = num.toInt(),
        name = name,
        types = types,
        img = img
    )

}
