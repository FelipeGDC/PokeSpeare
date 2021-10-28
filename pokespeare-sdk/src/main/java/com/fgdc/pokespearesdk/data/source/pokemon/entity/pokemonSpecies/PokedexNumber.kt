package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokedexNumber(
    @Json(name = "entry_number")
    var entryNumber: Int?
)
