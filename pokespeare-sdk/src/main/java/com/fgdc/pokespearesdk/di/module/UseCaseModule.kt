package com.fgdc.pokespearesdk.di.module

import com.fgdc.pokespearesdk.domain.repository.FuntranslationsRepository
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import com.fgdc.pokespearesdk.domain.usecases.GetPokemonDescription
import com.fgdc.pokespearesdk.domain.usecases.GetShakespeareanText
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetPokemonDescriptionUseCase(pokemonRepository: PokemonRepository): GetPokemonDescription =
        GetPokemonDescription(pokemonRepository)

    @Provides
    fun provideGetShakespeareanTextUseCase(funtranslationsRepository: FuntranslationsRepository): GetShakespeareanText =
        GetShakespeareanText(funtranslationsRepository)
}
