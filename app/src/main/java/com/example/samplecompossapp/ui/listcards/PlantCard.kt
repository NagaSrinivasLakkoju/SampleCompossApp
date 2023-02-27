package com.example.samplecompossapp.ui.listcards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samplecompossapp.ui.launchscreens.Screens

@Composable
fun PlantCard(
    name: String,
    description: String,
    image: Int,
    navController: NavHostController
) {
    val mContext = LocalContext.current
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navController.navigate(Screens.ListDetail)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,

                )
            Column(Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = name,
                    style = TextStyle(fontSize = 25.sp),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

