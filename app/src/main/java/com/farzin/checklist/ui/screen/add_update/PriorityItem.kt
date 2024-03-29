package com.farzin.checklist.ui.screen.add_update

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklist.model.add_update.PriorityModel
import com.farzin.checklist.ui.theme.darkText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityItem(
    priority: PriorityModel,
    onClick: () -> Unit,
    isSelected:Boolean,
) {



    Column {

        Card(
            modifier = Modifier
                .height(30.dp)
                .wrapContentWidth(),
            shape = Shapes().large,
            colors = if (isSelected) CardDefaults.cardColors(containerColor = priority.color) else CardDefaults.cardColors(containerColor = Color.Transparent),
            onClick = {
                onClick()
            },
            border = BorderStroke(2.dp,priority.color)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = priority.text,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.Center),
                )

            }


        }



    }


}