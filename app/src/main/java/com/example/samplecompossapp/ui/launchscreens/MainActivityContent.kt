
package com.example.samplecompossapp.ui.launchscreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.samplecompossapp.ui.registration.Login
import com.example.samplecompossapp.ui.registration.Signup
import com.example.samplecompossapp.ui.registration.UserDataScreen
import com.example.samplecompossapp.ui.viewmodel.MainViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun MainActivityContent(scaffoldState: ScaffoldState, mainViewModel: MainViewModel) {
    val navController = rememberAnimatedNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = Screens.Splash,
        ) {
            composable(Screens.Splash,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }){
                SplashScreen(navController)
            }
            composable(Screens.Menu){
                MenuScreen(navController)
            }
            composable(Screens.Login,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }
            ){
                Login(navController,scaffoldState)
            }
            composable(Screens.Signup,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }){
                Signup(navController)
            }

            composable(Screens.UserData+"/{name}/{email}/{password}",
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                arguments = listOf(
                    navArgument("name"){
                        type=NavType.StringType
                        defaultValue=""
                        nullable=true},
                    navArgument("email"){type= NavType.StringType
                        defaultValue=""
                        nullable=true},
                    navArgument("password"){type= NavType.StringType
                        defaultValue=""
                        nullable=true}
                )
            ){
                val name = it.arguments?.getString("name")
                val email = it.arguments?.getString("email")
                val password = it.arguments?.getString("password")
                UserDataScreen(navController,name,email,password)
            }

            composable(Screens.Home,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }){
                HomeScreen(mainViewModel)
            }

        }
    }
}

/*@Preview
@Composable
fun MainPreview() {
    MainActivityContent(scaffoldState)
}*/
