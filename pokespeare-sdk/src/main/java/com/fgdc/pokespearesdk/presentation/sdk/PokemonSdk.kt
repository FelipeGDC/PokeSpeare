package com.fgdc.pokespearesdk.presentation.sdk

import android.content.Context
import com.fgdc.pokespearesdk.di.component.ApplicationComponent
import com.fgdc.pokespearesdk.di.component.DaggerApplicationComponent
import com.fgdc.pokespearesdk.di.module.ApplicationModule
import com.fgdc.pokespearesdk.domain.mappers.toPokemonDescriptionSdk
import com.fgdc.pokespearesdk.domain.mappers.toPokemonSpriteSdk
import com.fgdc.pokespearesdk.domain.mappers.toShakespeareanTranslationSdk
import com.fgdc.pokespearesdk.domain.usecases.GetPokemonDescription
import com.fgdc.pokespearesdk.domain.usecases.GetPokemonSprite
import com.fgdc.pokespearesdk.domain.usecases.GetShakespeareanText
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonSdk(
    private val context: Context,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    @Inject
    lateinit var getPokemonDescription: GetPokemonDescription

    @Inject
    lateinit var getShakespeareanText: GetShakespeareanText

    @Inject
    lateinit var getPokemonSprite: GetPokemonSprite

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(context))
            .build()
    }

    init {
        appComponent.inject(this)
    }

    suspend fun getPokemonDescription(name: String): PokemonDescriptionSdk =
        withContext(defaultDispatcher) {
            when (val result =
                getPokemonDescription(GetPokemonDescription.Params(name.lowercase()))) {
                is State.Success -> {
                    val pokemonDescription =
                        result.data.toPokemonDescriptionSdk(result)
                    val resultTranslation = getShakespeareanText(
                        GetShakespeareanText.Params(pokemonDescription.description.orEmpty())
                    )
                    when (resultTranslation) {
                        is State.Success -> {
                            pokemonDescription.description =
                                resultTranslation.data.toShakespeareanTranslationSdk().textTranslated
                        }
                        else -> {
                        }
                    }
                    pokemonDescription
                }
                is State.BadRequest ->
                    PokemonDescriptionSdk(
                        state = State.BadRequest(
                            exception = result.exception,
                            message = result.exception.message.orEmpty()
                        )
                    )
                is State.Error -> PokemonDescriptionSdk(
                    state = State.Error(
                        exception = result.exception,
                        message = result.exception.message.orEmpty()
                    )
                )
                is State.ErrorNoConnection ->
                    PokemonDescriptionSdk(
                        state = State.BadRequest(
                            exception = result.exception,
                            message = result.exception.message.orEmpty()
                        )
                    )
            }
        }

    suspend fun getPokemonSprite(name: String): PokemonSpriteSdk =
        withContext(defaultDispatcher) {
            when (val result = getPokemonSprite(GetPokemonSprite.Params(name.lowercase()))) {
                is State.Success -> {
                    result.data.toPokemonSpriteSdk(result)
                }
                is State.BadRequest -> PokemonSpriteSdk(
                    state = State.BadRequest(
                        exception = result.exception,
                        message = result.exception.message.orEmpty()
                    )
                )
                is State.Error -> PokemonSpriteSdk(
                    state = State.Error(
                        exception = result.exception,
                        message = result.exception.message.orEmpty()
                    )
                )
                is State.ErrorNoConnection -> PokemonSpriteSdk(
                    state = State.BadRequest(
                        exception = result.exception,
                        message = result.exception.message.orEmpty()
                    )
                )
            }
        }
}
