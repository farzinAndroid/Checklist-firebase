package com.farzin.checklist.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.farzin.checklist.R
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.mainBackground

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    oneTapLoadingState: Boolean = false,
    googleSignInLoadingState:Boolean = false,
    primaryText: String = stringResource(R.string.sign_in_with_google),
    secondaryText: String = stringResource(R.string.please_wait),
    icon: Painter = painterResource(R.drawable.google_logo),
    shape: Shape = Shapes().extraSmall,
    borderColor: Color = MaterialTheme.colorScheme.darkText,
    backGroundColor: Color = MaterialTheme.colorScheme.mainBackground,
    borderStrokeWidth: Dp = 1.dp,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
) {


    var buttonText by remember {
        mutableStateOf(primaryText)
    }

    LaunchedEffect(oneTapLoadingState) {
        buttonText = if (oneTapLoadingState || googleSignInLoadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clickable(enabled = !oneTapLoadingState) { onClick() },
        shape = shape,
        border = BorderStroke(width = borderStrokeWidth, color = borderColor),
        color = backGroundColor,
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = tween(300, easing = LinearOutSlowInEasing),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = icon,
                contentDescription = "",
                tint = Color.Unspecified
            )

            Spacer(modifier = modifier.width(12.dp))

            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyMedium
            )

            if (oneTapLoadingState || googleSignInLoadingState) {
                Spacer(modifier = modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }

        }


    }


}

@Composable
@Preview
fun GoogleButtonPreview2() {
    GoogleButton(oneTapLoadingState = true) {}
}