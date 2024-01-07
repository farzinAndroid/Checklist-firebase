package com.farzin.checklist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.farzin.checklist.navGraph.SetUpNavGraph
import com.farzin.checklist.ui.theme.ChecklistTheme
import com.farzin.checklist.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChecklistTheme {

                val navController = rememberNavController()

                LocaleUtils.setLocale(this,"fa")

                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Rtl)) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            content = {
                                SetUpNavGraph(navController = navController)

                                val pdate = PersianDate()
                                val pdformater1 = PersianDateFormat("Y/m/d")
                                Log.e("TAG", pdate.toString())
                            }
                        )
                    }
                }

            }
        }
    }
}