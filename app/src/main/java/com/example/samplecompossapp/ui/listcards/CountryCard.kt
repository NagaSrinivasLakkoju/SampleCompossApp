package com.example.samplecompossapp.ui.listcards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountryCard() {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {

            Row(
                Modifier.padding(4.dp).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "",
                    fontSize = 30.sp
                )

               Spacer(modifier = Modifier.width(15.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        Text(
                            text = "",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .padding(4.dp))
                    }

                }
            }
        }
    }

}