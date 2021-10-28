package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "language")
    var language: Language?,
    @Json(name = "name")
    var name: String?
)
