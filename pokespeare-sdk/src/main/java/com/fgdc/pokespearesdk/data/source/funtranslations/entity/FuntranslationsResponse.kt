package com.fgdc.pokespearesdk.data.source.funtranslations.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FuntranslationsResponse(
    @Json(name = "contents")
    var contents: Contents?,
)
