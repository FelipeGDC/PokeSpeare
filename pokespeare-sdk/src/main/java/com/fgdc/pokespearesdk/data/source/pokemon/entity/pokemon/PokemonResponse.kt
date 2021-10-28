package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "sprites")
    var sprites: Sprite?,
    @Json(name = "types")
    var types: List<Types>?,
)
