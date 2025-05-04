package com.example.pokemon_api.service

import com.example.pokemon_api.dto.PokemonDto
import com.example.pokemon_api.model.Pokemon
import com.example.pokemon_api.model.PokemonType
import org.junit.jupiter.api.*
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.mockito.kotlin.whenever
import java.net.URI

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PokemonServiceTest {

    private lateinit var pokemonService: PokemonService
    private val pokemonJsonLoader: PokemonJsonLoader = mock()

    private val baseUrl = "http://example.com/img/"
    private lateinit var existingPokemon: Pokemon
    private lateinit var mockData: List<PokemonDto>

    private fun getMockData(): List<PokemonDto> {
        return listOf(
            PokemonDto("001", "Bulbasaur", listOf(PokemonType.GRASS), URI.create("${baseUrl}1.png")),
            PokemonDto("002", "Ivysaur", listOf(PokemonType.GRASS), URI.create("${baseUrl}2.png"))
        )
    }

    @BeforeEach
    fun setUp() {
        mockData = getMockData()
        whenever(pokemonJsonLoader.loadFromResource()).thenReturn(mockData)

        pokemonService = PokemonService(pokemonJsonLoader)

        existingPokemon = Pokemon(
            id = 1,
            name = "Bulbasaur",
            types = listOf(PokemonType.GRASS),
            img = URI("${baseUrl}1.png")
        )
    }

    @Test
    fun `should return all pokemons when data is loaded from the loader`() {
        val result = pokemonService.getAllPokemons()

        assertThat(result).hasSize(2)
        assertThat(result[0].name).isEqualTo("Bulbasaur")
        assertThat(result[1].name).isEqualTo("Ivysaur")
    }

    @Test
    fun `should return pokemon by id when exists`() {
        val result = pokemonService.getPokemonById(1)

        assertThat(result).isNotNull
        assertThat(result?.name).isEqualTo("Bulbasaur")
    }

    @Test
    fun `should return null when pokemon id does not exist`() {
        val result = pokemonService.getPokemonById(999)

        assertThat(result).isNull()
    }

    @Test
    fun `should return empty list when no pokemons are loaded`() {
        whenever(pokemonJsonLoader.loadFromResource()).thenReturn(emptyList())
        pokemonService = PokemonService(pokemonJsonLoader)

        val result = pokemonService.getAllPokemons()

        assertThat(result).isEmpty()
    }
}