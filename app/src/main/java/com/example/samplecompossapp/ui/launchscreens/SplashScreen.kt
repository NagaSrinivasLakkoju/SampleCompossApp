package com.example.samplecompossapp.ui.launchscreens

import android.media.Image
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.samplecompossapp.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecompossapp.BuildConfig

@Composable
fun SplashScreen(navController: NavController) = Box(
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0.0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(2000)



        navController.navigate(Screens.Login) {
            popUpTo(Screens.Splash) {
                inclusive = true
            }
        }


    }

    Image(
        painter = painterResource(id = R.drawable.nb),
        contentDescription = "",
        alignment = Alignment.Center, modifier = Modifier
            .fillMaxSize().padding(40.dp)
            .scale(scale.value)
    )

    Text(
        text = "Version - ${BuildConfig.VERSION_NAME}",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
    )
}