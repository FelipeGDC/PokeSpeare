package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.mappers.toShakespareTranslation
import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSource
import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.functional.State
import com.fgdc.pokespearesdk.utils.mockFuntranslationsText
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FuntranslationsRepositoryTest {

    @MockK
    lateinit var funtranslationsRemoteSource: FuntranslationsRemoteSource

    private lateinit var repository: FuntranslationsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository =
            FuntranslationsRepositoryImpl(funtranslationsRemoteSource)
    }

    @Test
    fun `should get text translated from service on success`(): Unit = runBlocking {
        val textTranslated = mockFuntranslationsText()
        val mockResponse =
            State.Success(textTranslated.toShakespareTranslation())
        val initialText =
            "You gave Mr. Tim a hearty meal, but unfortunately what he ate made him die"

        coEvery {
            funtranslationsRemoteSource.getTextTranslated(initialText)
        } returns mockResponse

        funtranslationsRemoteSource.getTextTranslated(initialText) shouldBe mockResponse

        val result: State<ShakespeareanTranslation> = repository.getTextTranslated(initialText)
        result.shouldBeInstanceOf<State.Success<Any>>()
        when (result) {
            is State.Success<ShakespeareanTranslation> -> {
                result.data shouldBe textTranslated.toShakespareTranslation()
            }
        }
    }
}
