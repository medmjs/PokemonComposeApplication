package com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemoncleanarchitectureapplication.R
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Stat
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Type
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.Pokemon
import com.example.pokemoncleanarchitectureapplication.utile.TypeParse
import com.example.pokemoncleanarchitectureapplication.utile.parseStatToAbbr
import com.example.pokemoncleanarchitectureapplication.utile.parseStatToColor
import java.util.*


@Composable
fun CreateInfoComponent(
    modifier: Modifier = Modifier,
    imageSize: Dp = 200.dp,
    defaultPadding: Dp = 60.dp,
    pokemon: Pokemon?
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = defaultPadding + imageSize / 2f,
                start = defaultPadding / 2,
                end = defaultPadding / 2,
                bottom = defaultPadding
            )
            .border(
                2.dp,
                Color.LightGray,
                RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(top = defaultPadding, start = 10.dp, end = 10.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            SectionName(pokemon)

            Spacer(modifier = Modifier.height(20.dp))

            TypeSection(modifier = Modifier, pokemon!!.types)

            Spacer(modifier = Modifier.height(20.dp))

            SeactionValues(pokemon)

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Base State :-", fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            SectionState(pokemon.stats)

        }

    }


}

@Composable
fun SectionState(stats: List<Stat>) {

    var maxValue = remember {
        stats.maxOf { it.base_stat }
    }


    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp)) {
        itemsIndexed(stats) { index, stat ->
            ItemState(stat, maxValue, durationMillis = index, delay = index)
        }
    }
}

@Composable
fun ItemState(stat: Stat, maxValue: Int, delay: Int, durationMillis: Int) {

    var animatePlay by remember {
        mutableStateOf(false)
    }

    val currentWidth =
        animateFloatAsState(
            targetValue = if (animatePlay) stat.base_stat / maxValue.toFloat() else 0f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            )
        )

    LaunchedEffect(key1 = true) {
        animatePlay = true
    }


    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .fillMaxWidth()
            .height(30.dp)
            .border(1.dp, Color.LightGray, CircleShape)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(currentWidth.value)
                .fillMaxHeight()
                .clip(CircleShape)
                .background(parseStatToColor(stat = stat))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text = parseStatToAbbr(stat))
            Text(text = (currentWidth.value * maxValue).toInt().toString())
        }


    }
}

@Composable
fun SeactionValues(pokemon: Pokemon) {

    Row(modifier = Modifier.fillMaxWidth()) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_weight),
                    contentDescription = "Weight"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = pokemon.weight.toString() + " Kg", fontSize = 18.sp)


            }

        }
        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(80.dp)
                .background(Color.LightGray)
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_height),
                    contentDescription = "height"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = pokemon.height.toString() + " mm", fontSize = 18.sp)


            }

        }

    }

}

@Composable
private fun SectionName(pokemon: Pokemon?) {
    Text(
        text = "#${pokemon!!.id}.${pokemon.name}",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    )
}

@Composable
fun TypeSection(modifier: Modifier, types: List<Type>) {

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        types.forEach {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(TypeParse(it))
                    .weight(1f)

            ) {

                Text(
                    text = it.type.name.capitalize(Locale.ROOT),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }
        }

    }


}

@Composable
fun TypeItem(item: Type, modifier: Modifier = Modifier) {


}
