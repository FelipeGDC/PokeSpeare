package com.fgdc.pokespeare.ui.search

import androidx.lifecycle.ViewModel
import com.fgdc.pokespearesdk.presentation.sdk.PokemonDescriptionSdk
import com.fgdc.pokespearesdk.presentation.sdk.PokemonSdk
import com.fgdc.pokespearesdk.presentation.sdk.PokemonSpriteSdk
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SearchViewModel @Inject constructor(private val pokemonSdk: PokemonSdk) : ViewModel() {

    private val _spinner = MutableStateFlow(false)
    val spinner: StateFlow<Boolean> = _spinner

    private val _pokemonDescription = MutableStateFlow(
        PokemonDescriptionSdk(
            state = null,
            description = null,
            generation = null,
            genera = null,
            name = null,
            pokedexNumber = null
        )
    )
    val pokemonDescription: StateFlow<PokemonDescriptionSdk> = _pokemonDescription

    private val _pokemonSprite =
        MutableStateFlow(PokemonSpriteSdk(state = null, url = "", type = ""))
    val pokemonSprite: StateFlow<PokemonSpriteSdk> = _pokemonSprite

    fun getPokemonInfo(pokemonName: String) {
        _spinner.value = true
        _pokemonDescription.value = pokemonSdk.getPokemonDescription(pokemonName)
        _pokemonSprite.value = pokemonSdk.getPokemonSprite(pokemonName)
        _spinner.value = false
    }
}
