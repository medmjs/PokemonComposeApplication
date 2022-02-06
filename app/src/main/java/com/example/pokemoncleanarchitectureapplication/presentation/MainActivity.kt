package com.example.pokemoncleanarchitectureapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen.component.PokemonInfoScreen
import com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen.component.PokemonListScreen
import com.example.pokemoncleanarchitectureapplication.presentation.ui.theme.PokemonCleanarchitectureApplicationTheme
import com.example.pokemoncleanarchitectureapplication.utile.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            PokemonCleanarchitectureApplicationTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(navController = navController)

            }
        }
    }
}


@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.PokemonListScreen.route
    ) {

        composable(Screen.PokemonListScreen.route) {
            PokemonListScreen(navController)
        }

        composable(Screen.PokemonInfoScreen.route + "/{pokemonName}/{pokemonColor}",
            arguments = listOf(
                navArgument("pokemonColor") {
                    type = NavType.IntType
                }

            )) {
            var color = Color.White
            it.arguments.let {
                color = Color(it!!.getInt("pokemonColor"))
            }




            PokemonInfoScreen(navController = navController, backGroundColor = color)

        }


    }

}
