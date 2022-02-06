package com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen.PoKemonInfoViewModel

@Composable
fun PokemonInfoScreen(
    navController: NavController,
    backGroundColor: Color = Color.White,
    viewModel: PoKemonInfoViewModel = hiltViewModel()
) {

    val pokemon = viewModel.state.value



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backGroundColor),
        contentAlignment = Alignment.TopCenter

    ) {

        if (pokemon.loading) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 5.dp
            )
        } else if (pokemon.pokemonInfo != null) {

            TopSection(navController = navController)

            CreateInfoComponent(
                modifier = Modifier,
                imageSize = 200.dp,
                defaultPadding = 60.dp,
                pokemon = pokemon.pokemonInfo
            )
            CreateImage(
                imageUrl = pokemon.pokemonInfo.sprites.front_default,
                imageSize = 200.dp,
                modifier = Modifier.align(Alignment.TopCenter),
                defaultPadding = 60.dp
            )


        } else if (pokemon.error!!.isNotBlank()) {

            ErrorComponent()

        }


    }


}


@Composable
fun ErrorComponent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "There's Something wrong ", color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { }) {
            Text(text = "Retry")

        }
    }

}
