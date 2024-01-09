package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.checklist.R
import com.farzin.checklist.ui.components.MySpacerWidth
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.extraSmall
import com.farzin.checklist.ui.theme.mainBackground

@Composable
fun SearchSection(onCardClicked:()->Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Card(
            modifier = Modifier
                .weight(.7f)
                .height(30.dp)
                .clickable { onCardClicked() },
            shape = Shapes().large,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.mainBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 8.dp)
                        .size(18.dp),
                    tint = MaterialTheme.colorScheme.darkText
                )

                Text(
                    text = stringResource(R.string.search),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(0.5f)
                )

            }
        }

        MySpacerWidth(width = 10.dp, modifier = Modifier.weight(.3f))
    }


}