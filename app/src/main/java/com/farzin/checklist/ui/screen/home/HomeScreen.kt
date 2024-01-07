package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun HomeScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
) {

    Home(authenticationViewModel, navController)
}

@Composable
fun Home(authenticationViewModel: AuthenticationViewModel, navController: NavController) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ){

        item { CalenderSection() }

    }



}