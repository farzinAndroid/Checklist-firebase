package com.farzin.checklist.ui.screen.add_update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.checklist.ui.components.MyDividerHorizontal
import com.farzin.checklist.ui.components.MySpacerWidth
import com.farzin.checklist.ui.theme.blueWithDarkTheme
import com.farzin.checklist.ui.theme.blueWithoutDarkTheme
import com.farzin.checklist.ui.theme.darkText

@Composable
fun AddUpdateTopBar(titleText:String,onClick:()->Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .weight(0.1f),
                tint = MaterialTheme.colorScheme.blueWithDarkTheme
            )
        }

        Text(
            text =titleText,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            textAlign = TextAlign.Center
        )

        MySpacerWidth(width = 10.dp, modifier = Modifier.weight(0.1f))

    }




    MyDividerHorizontal()

}