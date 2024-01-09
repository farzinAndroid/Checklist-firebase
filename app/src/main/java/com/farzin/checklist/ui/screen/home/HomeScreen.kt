package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.ui.components.MyDividerHorizontal
import com.farzin.checklist.ui.theme.mainBackground
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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
    ){

        TopBarSection(
            onCardClicked = {}
        )
        MyDividerHorizontal()

        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(100){
                Text(text = it.toString())
            }
        }


    }



}