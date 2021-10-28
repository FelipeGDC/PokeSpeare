package com.fgdc.pokespearesdk.domain.model

data class PokemonDescription(
    var description: String,
    var generation: String,
    var genera: String,
    var name: String,
    var pokedexNumber: Int
)

data class PokemonSprite(var url: String, var type: String)
