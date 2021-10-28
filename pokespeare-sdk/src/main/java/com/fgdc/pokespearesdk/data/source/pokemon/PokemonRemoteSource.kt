package com.fgdc.pokespearesdk.data.source.pokemon

import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.functional.State

interface PokemonRemoteSource {
    suspend fun getPokemonDescription(name: String): State<PokemonDescription>
    suspend fun getPokemonSprite(name: String): State<PokemonSprite>
}
