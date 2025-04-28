package com.example.pokemon_api.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class PokemonType(private val value: String) {

    BUG("Bug"),
    DRAGON("Dragon"),
    ELECTRIC("Electric"),
    FIGHTING("Fighting"),
    FIRE("Fire"),
    FLYING("Flying"),
    GHOST("Ghost"),
    GRASS("Grass"),
    GROUND("Ground"),
    ICE("Ice"),
    NORMAL("Normal"),
    POISON("Poison"),
    PSYCHIC("Psychic"),
    ROCK("Rock"),
    WATER("Water");

    @JsonValue
    fun toValue(): String = value

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromValue(value: String): PokemonType =
            entries.firstOrNull { it.value.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown type: $value")
    }
}
