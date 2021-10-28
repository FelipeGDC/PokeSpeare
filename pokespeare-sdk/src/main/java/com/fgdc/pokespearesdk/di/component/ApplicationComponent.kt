package com.fgdc.pokespearesdk.di.component

import com.fgdc.pokespearesdk.di.module.DataModule
import com.fgdc.pokespearesdk.di.module.NetworkModule
import com.fgdc.pokespearesdk.di.module.ApplicationModule
import com.fgdc.pokespearesdk.di.module.UseCaseModule
import com.fgdc.pokespearesdk.presentation.sdk.PokemonSdk
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, DataModule::class, UseCaseModule::class]
)
interface ApplicationComponent {

    fun inject(pokemonSdk: PokemonSdk)
}
