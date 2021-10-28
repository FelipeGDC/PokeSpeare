package com.fgdc.pokespearesdk.domain.mappers

import com.fgdc.pokespearesdk.domain.model.ShakespeareanTranslation
import com.fgdc.pokespearesdk.presentation.sdk.ShakespeareanTranslationSdk

fun ShakespeareanTranslation.toShakespeareanTranslationSdk() =
    ShakespeareanTranslationSdk(textTranslated = text)
