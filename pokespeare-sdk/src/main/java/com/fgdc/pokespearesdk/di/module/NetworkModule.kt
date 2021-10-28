package com.fgdc.pokespearesdk.di.module

import com.fgdc.pokespearesdk.utils.helpers.NetworkHandler
import com.fgdc.kotlin.library.android.BuildConfig
import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSource
import com.fgdc.pokespearesdk.data.source.funtranslations.FuntranslationsRemoteSourceImpl
import com.fgdc.pokespearesdk.data.source.funtranslations.api.FuntranslationsApi
import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSource
import com.fgdc.pokespearesdk.data.source.pokemon.PokemonRemoteSourceImpl
import com.fgdc.pokespearesdk.data.source.pokemon.api.PokemonApi
import com.fgdc.pokespearesdk.di.qualifiers.FuntranslationsRetrofit
import com.fgdc.pokespearesdk.di.qualifiers.PokemonRetrofit
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providePokemonRemoteSource(
        @PokemonRetrofit retrofit: Retrofit,
        networkHandler: NetworkHandler
    ): PokemonRemoteSource =
        PokemonRemoteSourceImpl(retrofit.create(PokemonApi::class.java), networkHandler)

    @Provides
    fun provideFuntranslationsRemoteSource(
        @FuntranslationsRetrofit retrofit: Retrofit,
        networkHandler: NetworkHandler
    ): FuntranslationsRemoteSource = FuntranslationsRemoteSourceImpl(
        retrofit.create(FuntranslationsApi::class.java), networkHandler
    )

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .followRedirects(false)
            .build()
    }

    @Provides
    @Singleton
    @PokemonRetrofit
    fun providePokemonRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(okHttpClient)
            .baseUrl(BuildConfig.POKEMON_API_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @FuntranslationsRetrofit
    fun provideFuntranslationsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(okHttpClient)
            .baseUrl(BuildConfig.FUNTRANSLATIONS_API_BASE_URL)
            .build()
    }
}
