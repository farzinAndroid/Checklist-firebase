package com.farzin.checklist.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farzin.checklist.ui.screen.home.HomeScreen
import com.farzin.checklist.ui.screen.authentication.CreateUserScreen
import com.farzin.checklist.ui.screen.authentication.SignInScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Screens.CreateUserScreen.route,
    ){
        composable(Screens.CreateUserScreen.route){
            CreateUserScreen(navController = navController)
        }


        composable(Screens.SignInScreen.route){
            SignInScreen(navController = navController)
        }

        composable(Screens.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }

}