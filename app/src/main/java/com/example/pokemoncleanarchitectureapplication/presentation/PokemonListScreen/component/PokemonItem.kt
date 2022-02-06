package com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.pokemoncleanarchitectureapplication.R
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Result
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonEntry
import com.example.pokemoncleanarchitectureapplication.utile.Screen
import com.example.pokemoncleanarchitectureapplication.utile.Until.Companion.getCurrentColor

@Composable
fun PokemonItem(
    pokemon: PokemonEntry? = null,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val painter = rememberImagePainter(data = pokemon?.pokemonImage)

    var painterStat = painter.state


    val defualtColor = MaterialTheme.colors.surface

    var currentColor by remember {
        mutableStateOf(defualtColor)

    }



    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable {
                pokemon?.let {
                    navController.navigate(Screen.PokemonInfoScreen.route + "/${it.pokemonName}/${currentColor.toArgb()}")
                }
            }
            .shadow(elevation = 5.dp, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(25.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        defualtColor,
                        currentColor
                    )
                ), RoundedCornerShape(25.dp)
            )
            .padding(bottom = 10.dp)


    ) {

        (painterStat as? ImagePainter.State.Loading)?.let {
            CircularProgressIndicator(
                color = Color.White
            )
        }

        (painterStat as? ImagePainter.State.Success)?.let { successStat ->

            val drawable = successStat.result.drawable
            getCurrentColor(drawable = drawable) {

                currentColor = it

            }

        }


        Image(
            painter = painter,
            contentDescription = pokemon?.pokemonName,
            modifier = Modifier.size(150.dp)
        )


        Text(
            text = pokemon?.pokemonName ?: "PokemonName",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }

}


@Preview
@Composable
fun previewPokemonItem() {

}