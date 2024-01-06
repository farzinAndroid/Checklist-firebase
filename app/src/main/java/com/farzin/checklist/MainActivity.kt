package com.farzin.checklist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.farzin.checklist.navGraph.SetUpNavGraph
import com.farzin.checklist.ui.theme.ChecklistTheme
import com.farzin.checklist.utils.Constants
import com.farzin.checklist.utils.UtilFunctions.getStartDestination
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChecklistTheme {

                val navController = rememberNavController()

                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            content = {
                                SetUpNavGraph(navController = navController)
                            }
                        )
                    }
                }

            }
        }
    }
}