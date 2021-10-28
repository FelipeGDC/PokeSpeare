package com.fgdc.pokespearesdk.data.source.funtranslations.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contents(
    @Json(name = "text")
    var text: String?,
    @Json(name = "translated")
    var translated: String?,
    @Json(name = "translation")
    var translation: String?
)
