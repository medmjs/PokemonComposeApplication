package com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Result
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonEntry

@Composable
fun PokemonRow(list: List<PokemonEntry>, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        list.forEach {
            PokemonItem(
                it,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 10.dp),
                navController = navController
            )
        }


    }
}