package com.gladiator.BasketBuddy.view.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController){

    NavHost(
        navController=navController,
        startDestination = "splash"
    ) {
        composable("splash"){
            SplashScreen(onNavigate = {navController.navigate("signup"){
                popUpTo("splash"){
                    inclusive=true
                }
                launchSingleTop=true
            } })
        }
        composable("signup"){
            SignUpScreen(navController,viewModel(), onSignUpSuccess = {
                navController.navigate("login"){
                    popUpTo("signup"){
                        inclusive=true
                    }
                    launchSingleTop=true
                }
            })
        }

        composable("login"){
            LoginScreen(navController,viewModel(), onLoginSuccess = {
                navController.navigate("home"){
            } })
        }

        composable("home"){
            HomeScreen(navController)
        }

        composable("collaboration"){
            Collaborations(hint = "search", onSearch = {}, navController = navController)
        }

        composable("listDisplay"){
            ListDisplayScreen(hint = String(), onSearch = {})
        }

        composable("itemDisplay"){
            ItemDisplayScreen(navController)
        }

        composable("addList"){
            AddListScreen(navController)
        }

        composable("addItem"){
            AddItemScreen(navController)
        }
    }
}