package com.farzin.checklist.ui.screen.add_update

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklist.R
import com.farzin.checklist.model.home.Subtask
import com.farzin.checklist.ui.components.MySpacerHeight
import com.farzin.checklist.ui.theme.blueWithoutDarkTheme
import com.farzin.checklist.ui.theme.darkText

@SuppressLint("MutableCollectionMutableState")
@Composable
fun SubTaskSection(subtask: List<Subtask> = emptyList(), subtaskCallback:(List<Subtask>)->Unit) {

    var subtasks by remember { mutableStateOf(subtask.toMutableList()) }

    var nextSubtaskId by remember {
        mutableIntStateOf((subtasks.maxOfOrNull { it.subtaskId } ?: 0) + 1)
    }

    // Only update the state if the parent list has changed
    LaunchedEffect(subtask) {
        if (subtask != subtasks) {
            subtasks.clear()
            subtasks.addAll(subtask)
            nextSubtaskId = (subtasks.maxOfOrNull { it.subtaskId } ?: 0) + 1
            subtaskCallback(subtasks)
        }
    }

    MySpacerHeight(height = 10.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 38.dp),
        horizontalAlignment = Alignment.Start
    ) {


        Text(
            text = stringResource(R.string.sub_task),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )


        MySpacerHeight(height = 8.dp)


        // Render existing text fields
        subtasks.forEachIndexed { index, subtask ->
            var updatedTitle by remember { mutableStateOf(subtask.title) }
            var isSubtaskCompleted by remember { mutableStateOf(subtask.subtaskCompleted) }

            SubTaskTextField(
                textValue = updatedTitle,
                onValueChanged = { newTitle ->
                    updatedTitle = newTitle
                    subtasks[index] = subtask.copy(title = newTitle)
                    subtaskCallback(subtasks)
                },
                isCompleted = isSubtaskCompleted,
                onCheckedChange = {
                    isSubtaskCompleted = !isSubtaskCompleted
                    subtasks[index] = subtask.copy(subtaskCompleted = isSubtaskCompleted)
                    subtaskCallback(subtasks)
                }
            )
        }


        MySpacerHeight(height = 16.dp)

        // Button to add another text field

        Button(onClick = {
            subtasks =
                (subtasks.toMutableList() + Subtask(subtaskId = nextSubtaskId++))
                    .toMutableList() // Add a new empty subtask
            subtaskCallback(subtasks)
        },
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.blueWithoutDarkTheme),
        ) {
           Text(
               text = stringResource(R.string.add_sub_task),
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.darkText,
               fontWeight = FontWeight.ExtraBold,
               fontSize = 14.sp
           )
        }

        /*Button(onClick = { Log.e("TAG",subtasks.toString()) }) {
            Text(text = "show")
        }*/


    }

}