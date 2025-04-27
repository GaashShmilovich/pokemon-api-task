package com.example.pokemon_api.model

data class Pokemon(
    val id: Int,
    val name: String,
    val type: List<String>,
    val num: String,
    val img: String
    )
