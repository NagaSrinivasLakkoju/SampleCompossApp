package com.example.samplecompossapp.ui.launchscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.data.menuItems
import com.example.samplecompossapp.ui.listcards.MenuCard

@Composable
fun MenuScreen(navController: NavHostController? = null) {
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
                Text(
                    "Compose Examples",
                    style = TextStyle(fontSize = 30.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(20.dp))

            }


            items(menuItems) { menu ->
                MenuCard(menu.menu, menu.desc)
            }
        }
    }

}

@Preview
@Composable
fun MenuPreView(){
    MenuScreen()
}

