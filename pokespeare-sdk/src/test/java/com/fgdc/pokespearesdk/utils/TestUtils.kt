package com.fgdc.pokespearesdk.utils

import com.fgdc.pokespearesdk.data.source.funtranslations.entity.Contents
import com.fgdc.pokespearesdk.data.source.funtranslations.entity.FuntranslationsResponse
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.PokemonResponse
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.Sprite
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.Type
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemon.Types
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.FlavorTextEntry
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.Genera
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.Generation
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.Language
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.Name
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.PokedexNumber
import com.fgdc.pokespearesdk.data.source.pokemon.entity.pokemonSpecies.PokemonSpeciesResponse

fun mockPokemon() = PokemonSpeciesResponse(
    flavorTextEntries = listOf(
        FlavorTextEntry(
            flavorText = "Pokemon description",
            language = Language("en", "")
        )
    ),
    genera = listOf(Genera(genus = "Pokemon Genus", language = Language("en", ""))),
    generation = Generation(name = "generation-i", url = ""),
    names = listOf(Name(language = Language("en", ""), name = "Pokemon name")),
    pokedexNumbers = listOf(PokedexNumber(1))
)

@Suppress("MaxLineLength")
fun mockSprite() = PokemonResponse(
    Sprite(
        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10189.png"
    ),
    listOf(Types(Type("bug")))
)

@Suppress("MaxLineLength")
fun mockFuntranslationsText() = FuntranslationsResponse(
    contents = Contents(
        text = "You gave Mr. Tim a hearty meal, but unfortunately what he ate made him die",
        translated = "Thee did giveth mr. Tim a hearty meal,  but unfortunately what he did doth englut did maketh him kicketh the bucket",
        translation = "shakespeare"
    )
)
