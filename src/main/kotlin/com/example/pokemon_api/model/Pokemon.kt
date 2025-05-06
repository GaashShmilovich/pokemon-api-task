package com.example.pokemon_api.model
import java.net.URI

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val img: URI?
)