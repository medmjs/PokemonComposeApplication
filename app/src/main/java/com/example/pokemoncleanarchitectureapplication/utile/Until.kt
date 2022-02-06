package com.example.pokemoncleanarchitectureapplication.utile

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

class Until {

    companion object{

        fun getCurrentColor(drawable: Drawable, onFinish: (Color) -> Unit) {

            val btm = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

            Palette.from(btm).generate { palette ->
                palette?.dominantSwatch?.rgb.let { colorValue ->
                    if (colorValue == null)
                        onFinish(Color.LightGray)
                    else
                        onFinish(Color(colorValue!!))
                }

            }


        }
    }
}