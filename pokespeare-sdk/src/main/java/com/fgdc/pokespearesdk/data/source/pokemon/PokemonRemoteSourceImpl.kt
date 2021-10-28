package com.fgdc.pokespearesdk.data.source.pokemon

import com.fgdc.pokespearesdk.utils.helpers.NetworkHandler
import com.fgdc.pokespearesdk.data.mappers.toPokemonDescription
import com.fgdc.pokespearesdk.data.mappers.toPokemonSprite
import com.fgdc.pokespearesdk.data.source.pokemon.api.PokemonApi
import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.exception.ErrorHandler
import com.fgdc.pokespearesdk.utils.functional.State

class PokemonRemoteSourceImpl(
    private val pokemonApi: PokemonApi,
    private val networkHandler: NetworkHandler
) : PokemonRemoteSource {
    override suspend fun getPokemonDescription(name: String): State<PokemonDescription> {
        if (!networkHandler.isInternetAvailable()) {
            return State.ErrorNoConnection(Throwable(ErrorHandler.NETWORK_ERROR_MESSAGE))
        }
        return try {
            pokemonApi.getPokemonDescription(name).run {
                if (isSuccessful && body() != null) {
                    State.Success(body()!!.toPokemonDescription())
                } else {
                    State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR))
        }
    }

    override suspend fun getPokemonSprite(name: String): State<PokemonSprite> {
        if (!networkHandler.isInternetAvailable()) {
            return State.ErrorNoConnection(Throwable(ErrorHandler.NETWORK_ERROR_MESSAGE))
        }
        return try {
            pokemonApi.getPokemonSprite(name).run {
                if (isSuccessful && body() != null) {
                    State.Success(body()!!.toPokemonSprite())
                } else {
                    State.BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return State.Error(Throwable(ErrorHandler.UNKNOWN_ERROR))
        }
    }
}
