package com.farzin.checklist.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farzin.checklist.ui.screen.authentication.CreateUserScreen
import com.farzin.checklist.ui.screen.home.HomeScreen
import com.farzin.checklist.utils.UtilFunctions.getStartDestination
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun SetUpNavGraph(navController: NavHostController,authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {

    val oneTapState = rememberOneTapSignInState()

    NavHost(
        navController = navController,
        startDestination = getStartDestination(authenticationViewModel),
    ){
        composable(Screens.CreateUserScreen.route){
            CreateUserScreen(navController = navController,oneTapState)
        }

        composable(Screens.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }

}