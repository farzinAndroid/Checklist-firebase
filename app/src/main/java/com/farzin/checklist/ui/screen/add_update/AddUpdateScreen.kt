package com.farzin.checklist.ui.screen.add_update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.checklist.R
import com.farzin.checklist.ui.components.MySpacerHeight
import com.farzin.checklist.ui.theme.mainBackground

@Composable
fun AddUpdateScreen(
    taskId: String,
    navController: NavController,
) {

    val titleText =
        if (taskId.isBlank()) stringResource(R.string.create_task) else stringResource(R.string.edit_task)

    var taskTitleText by remember { mutableStateOf("") }
    var taskDueTimeText by remember { mutableStateOf("") }
    var taskDueDateText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
    ) {

        item { AddUpdateTopBar(titleText = titleText, onClick = { navController.popBackStack() }) }

        item { MySpacerHeight(height = 12.dp) }

        item {
            TaskTitleSection(
                textValue = taskTitleText,
                onTextValueChanged = {
                    taskTitleText = it
                },
                title = stringResource(R.string.task_title),
                isHaveIcon = true
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueDateText,
                onTextValueChanged = {

                },
                title = stringResource(R.string.due_date),
                isHaveIcon = false
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueTimeText,
                onTextValueChanged = {

                },
                title = stringResource(R.string.due_time),
                isHaveIcon = false
            )
        }


    }


}