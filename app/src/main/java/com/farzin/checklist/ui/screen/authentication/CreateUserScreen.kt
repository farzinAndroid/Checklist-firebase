package com.farzin.checklist.ui.screen.authentication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.model.AuthResult
import com.farzin.checklist.model.NetworkResult
import com.farzin.checklist.navGraph.Screens
import com.farzin.checklist.utils.Constants
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle

@Composable
fun CreateUserScreen(navController: NavController, oneTapState: OneTapSignInState) {

    CreateUser(navController, oneTapState = oneTapState)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUser(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    oneTapState: OneTapSignInState,
) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    var googleDialogLoadingState by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = emailValue,
            onValueChange = { emailValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        OutlinedTextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                authenticationViewModel.createUser(
                    email = emailValue,
                    password = passwordValue,
                    onSuccess = {
                        navController.navigate(Screens.HomeScreen.route) {
                            launchSingleTop = true
                            popUpTo(Screens.CreateUserScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = "create")
        }


        Button(
            onClick = {
                oneTapState.open()
                googleDialogLoadingState = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
        ) {
            if (!googleDialogLoadingState) {
                Text(text = "create with google")
            } else {
                Text(text = "loading")
            }

        }

        OneTapSignInWithGoogle(
            state = oneTapState,
            clientId = Constants.WEB_Client_ID,
            onTokenIdReceived = { tokenId ->
                authenticationViewModel.createUserWithGoogleAuth(
                    tokenId = tokenId,
                    onSuccess = {
                        googleDialogLoadingState = false
                        navController.navigate(Screens.HomeScreen.route) {
                            launchSingleTop = true
                            popUpTo(Screens.CreateUserScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    onFailure = { message ->
                        googleDialogLoadingState = false
                        authenticationViewModel.googleSignInError = message
                    }
                )
            },
            onDialogDismissed = { message ->

            }
        )


        Text(text = "login", modifier = Modifier.clickable {
            navController.navigate(
                Screens.SignInScreen.route
            ) {
                launchSingleTop = true
            }
        })

        if (authenticationViewModel.createUserLoading) {
            Text(text = "loading")
        }

        if (!authenticationViewModel.isCreateUserSuccess) {
            Text(text = authenticationViewModel.createUserError)
        }


        if (authenticationViewModel.googleSignInLoading) {
            Text(text = "loading")
        }

        if (!authenticationViewModel.isGoogleSignSuccess) {
            Text(text = authenticationViewModel.googleSignInError)
        }


    }


}