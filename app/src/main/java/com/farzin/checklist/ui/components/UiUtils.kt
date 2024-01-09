package com.farzin.checklist.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.farzin.checklist.ui.theme.gray


@Composable
fun MySpacerHeight(modifier: Modifier = Modifier,height: Dp) {

    Spacer(modifier = modifier.height(height))

}

@Composable
fun MySpacerWidth(modifier: Modifier = Modifier,width: Dp) {

    Spacer(modifier = modifier.width(width))

}

@Composable
fun MyDividerHorizontal(modifier: Modifier = Modifier) {

    Divider(
        color = MaterialTheme.colorScheme.gray,
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    )

}

@Composable
fun MyDividerVertical(modifier: Modifier = Modifier) {

    Divider(
        color = MaterialTheme.colorScheme.gray,
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
    )

}