package com.example.pokemon_api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id", "name", "type", "img")
data class Pokemon(
    @JsonProperty("num") private val num: String,
    val name: String,
    val type: List<String>,
    val img: String
) {
    val id: Int
        get() = num.toInt()
}