package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.data.mappers.toShakespareTranslation
import com.fgdc.pokespearesdk.data.repository.FuntranslationsRepositoryImpl
import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockFuntranslationsText
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetShakespeareanTextTest {

    @MockK
    lateinit var repository: FuntranslationsRepositoryImpl

    private lateinit var getShakespeareanText: GetShakespeareanText

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getShakespeareanText = GetShakespeareanText(repository)
    }

    @Test
    fun `should get text translated on success`() = runBlocking {
        val textTranslated = mockFuntranslationsText()
        val mockResponse =
            flowOf(State.Success(textTranslated.toShakespareTranslation()))
        val initialText =
            "You gave Mr. Tim a hearty meal, but unfortunately what he ate made him die"

        coEvery {
            repository.getTextTranslated(initialText)
        } returns mockResponse

        val flow: Flow<State<ShakespeareanTranslation>> =
            getShakespeareanText.run(GetShakespeareanText.Params(initialText))

        (repository.getTextTranslated(initialText) == flow).shouldBeTrue()

        flow.collect { result ->
            result.shouldBeInstanceOf<State.Success<Any>>()
            when (result) {
                is State.Success<ShakespeareanTranslation> -> {
                    result.data shouldBe textTranslated.toShakespareTranslation()
                }
            }
        }

        verify(atLeast = 1) { repository.getTextTranslated(initialText) }
    }
}
