package com.example.samplecompossapp.ui.theme

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.samplecompossapp.R


val karla = FontFamily(
    Font(R.font.karla_regular),
    Font(R.font.karla_bold),
    Font(R.font.montserrat_medium)
)

val monster = FontFamily(
    Font(R.font.montserrat_medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),


    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h2 = TextStyle(
        fontFamily = karla,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorPrimaryDark
    ),

    h3 = TextStyle(
        fontFamily = monster,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        color = colorPrimaryDark
    )
)

