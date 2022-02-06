package com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemoncleanarchitectureapplication.R
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Result
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonEntry
import com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen.PokemonListViewModel
import com.example.pokemoncleanarchitectureapplication.utile.Screen

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {


        TopSection()

        ContentSection(navController, viewModel)

    }


}

@Composable
private fun ContentSection(navController: NavController, viewModel: PokemonListViewModel) {
    var result = viewModel.state.value


    var currentPage by remember {
        mutableStateOf(0)
    }

    if (result.loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    } else if (result.pokemons != null) {
        var pokemons = result.pokemons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 10.dp)

        ) {
            pokemons?.let {
                LazyColumn(

                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    var list = mutableListOf<PokemonEntry>()
                    itemsIndexed(pokemons!!) { index, pokemon ->
                        list.add(pokemon)
                        if (list.size == 2) {
                            PokemonRow(list, navController = navController)
                            list.clear()
                        }
                        if (pokemons.size - 1 == index) {
                            currentPage++
                            Log.d("TAG", "ContentSection: $currentPage")
                            viewModel.getPokemonList(currentPage * 20, 20)
                        }
                    }
                }
            }

        }


    } else if (result.error.isNotBlank()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Error Not Fount Image ", color = Color.Red, fontSize = 30.sp)
        }
    }
}

@Composable
fun TopSection(viewModel: PokemonListViewModel = hiltViewModel()) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 20.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            LogoSection()
            Spacer(modifier = Modifier.height(10.dp))
            SearchSection(hintText = "Search...") {
                viewModel.searchQuery(it)
            }

        }
    }


}

@Composable
private fun SearchSection(hintText: String, onSearch: (String) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }


    var isHint by remember {
        mutableStateOf(hintText != "")
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(CircleShape)
            .background(color = Color.White)
    ) {
        BasicTextField(
            value = text, onValueChange = { newText ->
                text = newText
                onSearch(newText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color = Color.White)
                .padding(10.dp)
                .onFocusChanged {
                    if (it.isFocused)
                        isHint = !isHint

                }
        )

        Text(text = if (isHint) hintText else "", modifier = Modifier.padding(start = 10.dp))

    }


}

@Composable
private fun LogoSection() {
    Image(
        painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
        contentDescription = "Logo"
    )
}

