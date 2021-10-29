package com.fgdc.pokespearesdk.data.mappers

import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.PokemonResponse
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.PokemonSpeciesResponse
import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.model.PokemonSprite
import com.fgdc.pokespearesdk.utils.extensions.escapeHtml

fun PokemonSpeciesResponse.toPokemonDescription() =
    PokemonDescription(
        description =
        flavorTextEntries!!.filter { it.language!!.name == "en" }[0].flavorText!!.escapeHtml(),
        generation = generation!!.name!!,
        genera = genera!!.filter { it.language!!.name == "en" }[0].genus!!,
        name = names!!.filter { it.language!!.name == "en" }[0].name!!,
        pokedexNumber = pokedexNumbers!![0].entryNumber!!

    )

fun PokemonResponse.toPokemonSprite() =
    PokemonSprite(url = sprites!!.frontDefault!!, type = types!![0].type!!.name!!)
