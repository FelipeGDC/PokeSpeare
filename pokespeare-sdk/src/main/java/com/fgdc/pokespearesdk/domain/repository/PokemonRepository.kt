package com.fgdc.pokespearesdk.domain.repository

import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.functional.State
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonDescription(name: String): State<PokemonDescription>

    suspend fun getPokemonSprite(name: String): State<PokemonSprite>
}
