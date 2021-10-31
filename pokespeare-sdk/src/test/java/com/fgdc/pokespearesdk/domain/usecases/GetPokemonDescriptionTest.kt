package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.data.mappers.toPokemonDescription
import com.fgdc.pokespearesdk.data.repository.PokemonRepositoryImpl
import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockPokemon
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonDescriptionTest {

    @MockK
    lateinit var repository: PokemonRepositoryImpl

    private lateinit var getPokemonDescription: GetPokemonDescription

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonDescription = GetPokemonDescription(repository)
    }

    @Test
    fun `should get pokemon description on success`() = runBlocking {
        val pokemon = mockPokemon()
        val mockResponse = State.Success(pokemon.toPokemonDescription())
        val name = "Butterfree"

        coEvery {
            repository.getPokemonDescription(name)
        } returns mockResponse

        val result: State<PokemonDescription> =
            getPokemonDescription.run(GetPokemonDescription.Params(name))

        result.shouldBeInstanceOf<State.Success<Any>>()
        when (result) {
            is State.Success<PokemonDescription> -> {
                result.data shouldBe pokemon.toPokemonDescription()
            }
        }
        coVerify(atLeast = 1) { repository.getPokemonDescription(name) }
    }
}
