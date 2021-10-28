package com.fgdc.pokespearesdk.di.module

import com.fgdc.pokespearesdk.data.repository.FuntranslationsRepositoryImpl
import com.fgdc.pokespearesdk.data.repository.PokemonRepositoryImpl
import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSource
import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSource
import com.fgdc.pokespearesdk.domain.repository.FuntranslationsRepository
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providePokemonRepository(
        pokemonRemoteSource: PokemonRemoteSource
    ): PokemonRepository = PokemonRepositoryImpl(pokemonRemoteSource)

    @Provides
    fun provideFuntranslationsRepository(
        funtranslationsRemoteSource: FuntranslationsRemoteSource
    ): FuntranslationsRepository = FuntranslationsRepositoryImpl(funtranslationsRemoteSource)
}
