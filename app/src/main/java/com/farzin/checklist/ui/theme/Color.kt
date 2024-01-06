package com.farzin.checklist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.mainBlue: Color
    @Composable
    get() =  Color(0xFF1a73e9)

val ColorScheme.mainBackground: Color
    @Composable
    get() =  if (isSystemInDarkTheme()) Color(0xFF3a3a3a) else Color(0xFFffffff)

val ColorScheme.darkText: Color
    @Composable
    get() =  if (isSystemInDarkTheme()) Color(0xFFffffff) else Color(0xFF3a3a3a)

val ColorScheme.gray: Color
    @Composable
    get() = Color(0xFF626262)

val ColorScheme.softgray: Color
    @Composable
    get() = Color(0xFFf2eded)
