package com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpeciesResponse(
    @Json(name = "flavor_text_entries")
    var flavorTextEntries: List<FlavorTextEntry>?,
    @Json(name = "genera")
    var genera: List<Genera>?,
    @Json(name = "generation")
    var generation: Generation?,
    @Json(name = "names")
    var names: List<Name>?,
    @Json(name = "pokedex_numbers")
    var pokedexNumbers: List<PokedexNumber>?
)
