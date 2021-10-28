package com.fgdc.pokespeare.utils.extensions

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load

fun ImageView.simpleLoad(url: String, context: Context) {
    this.load(
        url,
        ImageLoader.Builder(context).componentRegistry {
            add(SvgDecoder(context))
        }.build()
    )
}
