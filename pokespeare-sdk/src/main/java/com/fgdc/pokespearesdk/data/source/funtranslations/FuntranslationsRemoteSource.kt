package com.fgdc.pokespearesdk.data.source.funtranslations

import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.functional.State

interface FuntranslationsRemoteSource {
    suspend fun getTextTranslated(text: String): State<ShakespeareanTranslation>
}
