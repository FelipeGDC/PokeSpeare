package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.data.mappers.toShakespareTranslation
import com.fgdc.pokespearesdk.data.repository.FuntranslationsRepositoryImpl
import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockFuntranslationsText
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
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
            State.Success(textTranslated.toShakespareTranslation())
        val initialText =
            "You gave Mr. Tim a hearty meal, but unfortunately what he ate made him die"

        coEvery {
            repository.getTextTranslated(initialText)
        } returns mockResponse

        val result: State<ShakespeareanTranslation> =
            getShakespeareanText.run(GetShakespeareanText.Params(initialText))

        result.shouldBeInstanceOf<State.Success<Any>>()
        when (result) {
            is State.Success<ShakespeareanTranslation> -> {
                result.data shouldBe textTranslated.toShakespareTranslation()
            }
        }

        coVerify(atLeast = 1) { repository.getTextTranslated(initialText) }
    }
}
