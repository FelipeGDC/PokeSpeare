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
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PokemonSdk(private val context: Context) {

    @Inject
    lateinit var getPokemonDescription: GetPokemonDescription

    @Inject
    lateinit var getShakespeareanText: GetShakespeareanText

    @Inject
    lateinit var getPokemonSprite: GetPokemonSprite

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(context))
            .build()
    }

    init {
        appComponent.inject(this)
    }

    fun getPokemonDescription(name: String): PokemonDescriptionSdk {
        lateinit var pokemonDescription: PokemonDescriptionSdk
        runBlocking {
            val pokemonRequest = async {
                getPokemonDescription(GetPokemonDescription.Params(name.lowercase())).collect { result ->
                    when (result) {
                        is State.Success -> {
                            pokemonDescription =
                                result.data.toPokemonDescriptionSdk(result)
                        }
                        is State.BadRequest -> pokemonDescription = PokemonDescriptionSdk(
                            state = State.BadRequest(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                        is State.Error -> pokemonDescription = PokemonDescriptionSdk(
                            state = State.Error(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                        is State.ErrorNoConnection -> pokemonDescription = PokemonDescriptionSdk(
                            state = State.BadRequest(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                    }
                }
            }
            pokemonRequest.await()
            async {
                getShakespeareanText(
                    GetShakespeareanText.Params(pokemonDescription.description.orEmpty())
                ).collect { result ->
                    when (result) {
                        is State.Success -> {
                            pokemonDescription.description =
                                result.data.toShakespeareanTranslationSdk().textTranslated
                        }
                    }
                }
            }
        }
        return pokemonDescription
    }

    fun getPokemonSprite(name: String): PokemonSpriteSdk {
        lateinit var pokemonSprite: PokemonSpriteSdk
        runBlocking {
            launch {
                getPokemonSprite(GetPokemonSprite.Params(name.lowercase())).collect { result ->
                    when (result) {
                        is State.Success -> {
                            pokemonSprite =
                                result.data.toPokemonSpriteSdk(result)
                        }
                        is State.BadRequest -> pokemonSprite = PokemonSpriteSdk(
                            state = State.BadRequest(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                        is State.Error -> pokemonSprite = PokemonSpriteSdk(
                            state = State.Error(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                        is State.ErrorNoConnection -> pokemonSprite = PokemonSpriteSdk(
                            state = State.BadRequest(
                                exception = result.exception,
                                message = result.exception.message.orEmpty()
                            )
                        )
                    }
                }
            }
        }
        return pokemonSprite
    }
}
