package com.fgdc.pokespearesdk.data.mappers

import android.text.Html
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.PokemonResponse
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.PokemonSpeciesResponse
import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite

fun PokemonSpeciesResponse.toPokemonDescription() =
    PokemonDescription(
        description = Html.fromHtml(
            flavorTextEntries!!.filter { it.language!!.name == "en" }[0].flavorText!!,
            Html.FROM_HTML_MODE_COMPACT
        ).toString(),
        generation = generation!!.name!!,
        genera = genera!!.filter { it.language!!.name == "en" }[0].genus!!,
        name = names!!.filter { it.language!!.name == "en" }[0].name!!,
        pokedexNumber = pokedexNumbers!![0].entryNumber!!

    )

fun PokemonResponse.toPokemonSprite() =
    PokemonSprite(url = sprites!!.frontDefault!!, type = types!![0].type!!.name!!)
