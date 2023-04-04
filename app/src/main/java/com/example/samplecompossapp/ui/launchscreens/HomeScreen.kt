package com.example.samplecompossapp.ui.launchscreens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samplecompossapp.R
import com.example.samplecompossapp.bottom_navigation.BottomNavigationBar
import com.example.samplecompossapp.bottom_navigation.NavigationItem
import com.example.samplecompossapp.ui.home.*
import com.example.samplecompossapp.ui.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        //  topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = colorResource(R.color.white)



    )
}

@SuppressLint("NewApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.HomeView.route) {
        composable(NavigationItem.HomeView.route) {
            HomeView()
        }
        composable(NavigationItem.MoviesView.route) {
            MoviesView()
        }
        composable(NavigationItem.PlantsView.route) {
            PlantsView(navController)
        }
        composable(NavigationItem.BooksView.route) {
            BooksView()
        }
        composable(NavigationItem.ProfileView.route) {
            ProfileView()
        }

        composable(Screens.ListDetail) {
            ListDetailScreen()
        }
    }
}

