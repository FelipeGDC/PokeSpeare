package com.fgdc.pokespearesdk.data.repository

import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSource
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import com.fgdc.pokespearesdk.utils.exception.ErrorHandler
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl @Inject constructor(private val pokemonRemoteSource: PokemonRemoteSource) :
    PokemonRepository {
    override fun getPokemonDescription(name: String) = flow {
        emit(pokemonRemoteSource.getPokemonDescription(name))
    }.catch {
        it.printStackTrace()
        emit(State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR)))
    }

    override fun getPokemonSprite(name: String) = flow {
        emit(pokemonRemoteSource.getPokemonSprite(name))
    }.catch {
        it.printStackTrace()
        emit(State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR)))
    }
}
