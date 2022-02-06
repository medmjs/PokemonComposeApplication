package com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokemoncleanarchitectureapplication.presentation.ui.theme.Shapes

@Composable
fun CreateImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageSize: Dp = 200.dp,
    defaultPadding: Dp = 60.dp
) {

    val painter = rememberImagePainter(data = imageUrl)


    Image(
        painter = painter,
        contentDescription = "Pokemon Image",
        modifier = modifier
            .padding(defaultPadding)
            .size(imageSize)

    )


}