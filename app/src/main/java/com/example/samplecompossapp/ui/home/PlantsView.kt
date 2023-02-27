package com.example.samplecompossapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.R
import com.example.samplecompossapp.data.plants
import com.example.samplecompossapp.ui.listcards.PlantCard


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlantsView(navController: NavHostController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val lazyListState = rememberLazyListState()
        var scrolledY = 0f
        var previousOffset = 0

        LazyColumn(
            Modifier.fillMaxSize(),
            lazyListState,
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.flower),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .graphicsLayer {
                            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        }
                        .height(240.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "\uD83C\uDF3F  Plants in Cosmetics",
                        style = TextStyle(fontSize = 60.sp)
                    )
                }
            }
            items(plants) { plant ->
                PlantCard(plant.name, plant.description, plant.imageRes,navController)
            }
        }


    }

}

