package com.fgdc.pokespearesdk.data.mappers

import com.fgdc.pokespearesdk.data.source.funtranslations.entity.FuntranslationsResponse
import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation

fun FuntranslationsResponse.toShakespareTranslation() =
    ShakespeareanTranslation(text = contents!!.translated!!)
