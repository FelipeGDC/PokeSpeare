package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.mappers.toPokemonDescription
import com.fgdc.pokespearesdk.data.mappers.toPokemonSprite
import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSource
import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockPokemon
import com.fgdc.pokespearesdk.utils.mockSprite
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonRepositoryTest {

    @MockK
    lateinit var pokemonRemoteSource: PokemonRemoteSource

    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository =
            PokemonRepositoryImpl(pokemonRemoteSource)
    }

    @Test
    fun `should get specific pokemon info from service on success`(): Unit = runBlocking {
        val pokemon = mockPokemon()
        val mockResponse =
            State.Success(pokemon.toPokemonDescription())
        val pokemonName = "Butterfree"

        coEvery {
            pokemonRemoteSource.getPokemonDescription(pokemonName)
        } returns mockResponse

        pokemonRemoteSource.getPokemonDescription(pokemonName) shouldBe mockResponse

        val flow: Flow<State<PokemonDescription>> = repository.getPokemonDescription(pokemonName)
        flow.collect { result ->
            result.shouldBeInstanceOf<State.Success<Any>>()
            when (result) {
                is State.Success<PokemonDescription> -> {
                    result.data shouldBe pokemon.toPokemonDescription()
                }
            }
        }
    }

    @Test
    fun `should get specific pokemon sprite from service on success`(): Unit = runBlocking {
        val pokemonSprite = mockSprite()
        val mockResponse =
            State.Success(pokemonSprite.toPokemonSprite())
        val pokemonName = "Butterfree"

        coEvery {
            pokemonRemoteSource.getPokemonSprite(pokemonName)
        } returns mockResponse

        pokemonRemoteSource.getPokemonSprite(pokemonName) shouldBe mockResponse

        val flow: Flow<State<PokemonSprite>> = repository.getPokemonSprite(pokemonName)
        flow.collect { result ->
            result.shouldBeInstanceOf<State.Success<Any>>()
            when (result) {
                is State.Success<PokemonSprite> -> {
                    result.data shouldBe pokemonSprite.toPokemonSprite()
                }
            }
        }
    }
}
