package com.fgdc.pokespearesdk.presentation.sdk

import com.fgdc.pokespearesdk.utils.functional.State

data class PokemonDescriptionSdk(
    var state: State<Any>?,
    var description: String? = "",
    var generation: String? = "",
    var genera: String? = "",
    var name: String? = "",
    var pokedexNumber: Int? = 0
)

data class PokemonSpriteSdk(var state: State<Any>?, var url: String = "", var type: String = "")

data class ShakespeareanTranslationSdk(
    var textTranslated: String
)
