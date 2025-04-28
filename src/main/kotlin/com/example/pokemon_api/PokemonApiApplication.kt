package com.example.pokemon_api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import java.io.InputStreamReader

@SpringBootApplication
class PokemonApiApplication

fun main(args: Array<String>) {
	runApplication<PokemonApiApplication>(*args)
}
