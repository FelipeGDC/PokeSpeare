package com.fgdc.pokespearesdk.domain.repository

import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.functional.State
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonDescription(name: String): Flow<State<PokemonDescription>>

    fun getPokemonSprite(name: String): Flow<State<PokemonSprite>>
}
