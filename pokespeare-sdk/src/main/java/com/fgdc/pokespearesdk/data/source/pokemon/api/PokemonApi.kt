package com.fgdc.pokespearesdk.data.source.pokemon.api

import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.PokemonResponse
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    companion object {
        private const val POKEMON_SPECIES_ENDPOINT = "pokemon-species"
        private const val POKEMON_ENDPOINT = "pokemon"
    }

    @GET(POKEMON_SPECIES_ENDPOINT.plus("/{name}"))
    suspend fun getPokemonDescription(@Path("name") name: String): Response<PokemonSpeciesResponse>

    @GET(POKEMON_ENDPOINT.plus("/{name}"))
    suspend fun getPokemonSprite(@Path("name") name: String): Response<PokemonResponse>
}
