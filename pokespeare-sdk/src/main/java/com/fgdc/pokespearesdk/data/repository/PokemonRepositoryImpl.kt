package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSource
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonRemoteSource: PokemonRemoteSource) :
    PokemonRepository {
    override suspend fun getPokemonDescription(name: String) =
        pokemonRemoteSource.getPokemonDescription(name)

    override suspend fun getPokemonSprite(name: String) = pokemonRemoteSource.getPokemonSprite(name)
}
