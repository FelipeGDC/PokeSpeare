package com.fgdc.pokespearesdk.domain.repository

import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.utils.functional.State
import kotlinx.coroutines.flow.Flow

interface FuntranslationsRepository {
   suspend fun getTextTranslated(text: String): State<ShakespeareanTranslation>
}
