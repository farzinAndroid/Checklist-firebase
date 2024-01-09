package com.farzin.checklist.ui.screen.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.R
import com.farzin.checklist.navGraph.Screens
import com.farzin.checklist.ui.components.GoogleButton
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.mainBackground
import com.farzin.checklist.utils.Constants
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle

@Composable
fun CreateUserScreen(navController: NavController, oneTapState: OneTapSignInState) {

    CreateUser(navController, oneTapState = oneTapState)

}


@Composable
fun CreateUser(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    oneTapState: OneTapSignInState,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Column(
            modifier = Modifier
                .weight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.google_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.welcome_back),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.darkText
                )
                Text(
                    color = MaterialTheme.colorScheme.darkText.copy(alpha = 0.38f),
                    text = stringResource(id = R.string.please_sign_in_to_continue),
                    style = MaterialTheme.typography.headlineSmall
                )

            }

        }

        Column(
            modifier = Modifier
                .weight(0.4f)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            GoogleButton(
                oneTapLoadingState = oneTapState.opened,
                googleSignInLoadingState = authenticationViewModel.googleSignInLoading,
                onClick = {
                    oneTapState.open()
                }
            )

        }


        OneTapSignInWithGoogle(
            state = oneTapState,
            clientId = Constants.WEB_Client_ID,
            onTokenIdReceived = { tokeId ->
                authenticationViewModel.createUserWithGoogleAuth(
                    tokenId = tokeId,
                    onSuccess = {tokenId->
                        navController.navigate(Screens.HomeScreen.route) {
                            launchSingleTop = true
                            popUpTo(Screens.CreateUserScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    onFailure = {message->
                        authenticationViewModel.googleSignInError = message
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                        Log.e("TAG",authenticationViewModel.googleSignInError)
                    }
                )
            },
            onDialogDismissed = { message ->
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }
        )

    }

}
