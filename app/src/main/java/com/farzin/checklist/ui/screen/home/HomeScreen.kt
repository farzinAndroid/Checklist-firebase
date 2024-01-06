package com.farzin.checklist.ui.screen.home

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun HomeScreen(navController: NavController,authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {

    Button(onClick = { Firebase.auth.signOut() }) {
        Text(text = "12231", style = MaterialTheme.typography.titleLarge)
    }
}