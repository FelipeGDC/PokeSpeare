package com.fgdc.pokespearesdk.domain.mappers

import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.presentation.sdk.PokemonDescriptionSdk
import com.fgdc.pokespearesdk.presentation.sdk.PokemonSpriteSdk
import com.fgdc.pokespearesdk.utils.functional.State

fun PokemonDescription.toPokemonDescriptionSdk(state: State<Any>) = PokemonDescriptionSdk(
    state = state,
    description = description,
    generation = generation,
    genera = genera,
    name = name,
    pokedexNumber = pokedexNumber
)

fun PokemonSprite.toPokemonSpriteSdk(state: State<Any>) = PokemonSpriteSdk(state = state, url = url, type = type)
