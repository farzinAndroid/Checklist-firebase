package com.farzin.checklist.ui.screen.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farzin.checklist.R
import com.farzin.checklist.model.Task
import com.farzin.checklist.ui.components.MySpacerWidth
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.mainBackground
import com.farzin.checklist.ui.theme.softgray
import com.farzin.checklist.ui.theme.veryExtraSmall
import com.hitanshudhawan.circularprogressbar.CircularProgressBar

@Composable
fun TaskItem(task: Task) {


    var progress by remember {
        mutableFloatStateOf(0.5f)
    }

    val progress2 by animateFloatAsState(progress*100f, label = "")


    val color = when(task.priority){
        1->{
            Color.Red
        }
        2->{
            Color.Black
        }
        3->{
            Color.Green
        }
        else->{
            Color.Gray
        }
    }



    Card(
        modifier = Modifier
            .padding(vertical = 18.dp)
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 16.dp)
            .shadow(6.dp, shape = Shapes().extraLarge, spotColor = MaterialTheme.colorScheme.darkText),
        shape = Shapes().extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.mainBackground),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.9f)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressBar(
                        progress = progress2,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = 0.dp,
                                color = Color.Transparent,
                                shape = CircleShape
                            ),
                        progressMax = 100f,
                        progressBarWidth = 4.dp,
                        roundBorder = true,
                        progressBarColor = color,
                        backgroundProgressBarColor = color.copy(0.5f),
                        backgroundProgressBarWidth = 4.dp
                    )

                    Text(
                        text = "75%",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.Bold
                    )
                }


                MySpacerWidth(width = 16.dp)

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.displaySmall,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        overflow = TextOverflow.Ellipsis
                    )


                    Text(
                        text = "${task.subTask.size} ${stringResource(R.string.tasks)}",
                        style = MaterialTheme.typography.veryExtraSmall,
                        color = MaterialTheme.colorScheme.softgray,
                        fontWeight = FontWeight.Bold,
                    )

                }



            }


            Row(
                modifier = Modifier
                    .weight(0.1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {

                Box(
                    modifier = Modifier
                        .clip(Shapes().extraLarge)
                        .background(color)
                        .fillMaxHeight()
                        .width(6.dp)
                        .padding(16.dp)


                )

            }


        }

    }


}