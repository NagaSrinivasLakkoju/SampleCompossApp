package com.example.samplecompossapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import com.example.samplecompossapp.ui.launchscreens.MainActivityContent
import com.example.samplecompossapp.ui.theme.SampleCompossAppTheme
import com.example.samplecompossapp.ui.viewmodel.MainViewModel
import com.example.samplecompossapp.utils.TopBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //val movieViewModel by viewModels<MovieViewModel>()
    val mainViewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState: ScaffoldState = rememberScaffoldState()
            SampleCompossAppTheme {

              //  DrawerMainScreen()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { TopBar() },
                    content = { MainActivityContent(scaffoldState,mainViewModel)})
            }
        }
    }
}


