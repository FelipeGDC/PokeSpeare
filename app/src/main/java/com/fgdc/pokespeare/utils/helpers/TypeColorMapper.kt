package com.fgdc.pokespeare.utils.helpers

import android.content.Context
import androidx.core.content.ContextCompat
import com.fgdc.pokespeare.R
import java.util.Locale

class TypeColorMapper(private val context: Context) {

    fun mapPokemonTypeToColor(typeOfPokemon: String): Int {
        val color = when (typeOfPokemon.lowercase(Locale.ROOT)) {
            "grass", "bug" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> R.color.lightBlue
        }
        return ContextCompat.getColor(context, color)
    }
}
