package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genera(
    @Json(name = "genus")
    var genus: String?,
    @Json(name = "language")
    var language: Language?
)
