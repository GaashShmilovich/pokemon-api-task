package com.example.pokemon_api.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class PokemonTypeDeserializer : JsonDeserializer<PokemonType>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PokemonType {
        val value = p.text
        return PokemonType.entries.firstOrNull { it.name.equals(value, ignoreCase = true) }
            ?: throw IllegalArgumentException("Unknown PokemonType: $value")
    }
}