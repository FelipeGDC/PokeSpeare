package com.fgdc.pokespearesdk.presentation.component

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.fgdc.kotlin.library.android.R

class PokemonInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var name: AppCompatTextView
    var description: AppCompatTextView
    var pokedexNumber: AppCompatTextView
    var sprite: AppCompatImageView
    var infoViewWrapper: ConstraintLayout

    init {
        inflate(context, R.layout.pokemon_info_view, this)
        name = findViewById(R.id.pokemonName)
        description = findViewById(R.id.pokemonDescription)
        pokedexNumber = findViewById(R.id.pokemonId)
        sprite = findViewById(R.id.pokemonSprite)
        infoViewWrapper = findViewById(R.id.infoViewWrapper)
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PokemonInfoView,
            0,
            0
        )

        typedArray.recycle()
    }
}
