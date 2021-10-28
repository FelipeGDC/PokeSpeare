package com.fgdc.pokespeare.di

import android.content.Context
import com.fgdc.pokespearesdk.presentation.sdk.PokemonSdk
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SdkModule {

    @Provides
    fun providePokemonSdk(@ApplicationContext context: Context): PokemonSdk =
        PokemonSdk(context = context)
}
