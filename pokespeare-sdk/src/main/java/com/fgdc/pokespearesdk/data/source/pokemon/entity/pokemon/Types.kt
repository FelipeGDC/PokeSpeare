package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Types(
    @Json(name = "type")
    var type: Type?
)
