package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.data.mappers.toPokemonSprite
import com.fgdc.pokespearesdk.data.repository.PokemonRepositoryImpl
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockSprite
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonSpriteTest {

    @MockK
    lateinit var repository: PokemonRepositoryImpl

    private lateinit var getPokemonSprite: GetPokemonSprite

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonSprite = GetPokemonSprite(repository)
    }

    @Test
    fun `should get pokemon sprite on success`() = runBlocking {
        val pokemon = mockSprite()
        val mockResponse = State.Success(pokemon.toPokemonSprite())
        val name = "Butterfree"

        coEvery {
            repository.getPokemonSprite(name)
        } returns mockResponse

        val result: State<PokemonSprite> =
            getPokemonSprite.run(GetPokemonSprite.Params(name))

        result.shouldBeInstanceOf<State.Success<Any>>()
        when (result) {
            is State.Success<PokemonSprite> -> {
                result.data shouldBe pokemon.toPokemonSprite()
            }
        }
        coVerify(atLeast = 1) { repository.getPokemonSprite(name) }
    }
}
