package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @Json(name = "flavor_text")
    var flavorText: String?,
    @Json(name = "language")
    var language: Language?,
)
