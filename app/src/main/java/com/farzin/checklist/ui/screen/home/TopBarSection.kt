package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.checklist.R
import com.farzin.checklist.ui.components.MyDividerHorizontal
import com.farzin.checklist.ui.components.MyDividerVertical
import com.farzin.checklist.ui.components.MySpacerHeight
import com.farzin.checklist.ui.theme.darkText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun TopBarSection(onCardClicked:()->Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = painterResource(R.drawable.strawberry_pie_1),
            contentDescription ="",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Red, CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.my_tasks),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            MySpacerHeight(height = 8.dp)

            SearchSection{
                onCardClicked()
            }

            MySpacerHeight(height = 16.dp)






        }

    }

}