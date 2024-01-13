package com.farzin.checklist.ui.screen.add_update

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.R
import com.farzin.checklist.model.home.Subtask
import com.farzin.checklist.model.home.Task
import com.farzin.checklist.ui.components.MySpacerHeight
import com.farzin.checklist.ui.theme.mainBackground
import com.farzin.checklist.viewModel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddUpdateScreen(
    taskId: String,
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
) {

    var singleTask by remember { mutableStateOf(Task()) }
    if (taskId.isNotEmpty()){
        LaunchedEffect(taskId){
            taskViewModel.getSingleTask(taskId)

            taskViewModel.singleTask.collectLatest {result->
                singleTask = result
            }
        }
    }


    val titleText =
        if (taskId.isBlank()) stringResource(R.string.create_task) else stringResource(R.string.edit_task)

    var taskTitleText by remember { mutableStateOf("") }
    singleTask.let {
        taskTitleText = it.title
    }
    var taskDueTimeText by remember { mutableStateOf("") }
    singleTask.let {
        taskDueTimeText = it.dueTime
    }
    var taskDueDateText by remember { mutableStateOf("") }
    singleTask.let {
        taskDueDateText = it.dueDate
    }
    var priorityNumber by remember { mutableIntStateOf(-1) }
    singleTask.let {
        priorityNumber = it.priority
    }
    var subTasks by remember { mutableStateOf(emptyList<Subtask>()) }
    singleTask.let {
        subTasks = it.subTask
    }

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
                isHaveIcon = false
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueDateText,
                onTextValueChanged = {

                },
                title = stringResource(R.string.due_date),
                isHaveIcon = true
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueTimeText,
                onTextValueChanged = {

                },
                title = stringResource(R.string.due_time),
                isHaveIcon = true
            )
        }

        item {
            PrioritySection(priorityNumber = if (taskId.isNotEmpty()) priorityNumber else -1)
        }


        item { SubTaskSection(subtask = if (taskId.isNotEmpty()) subTasks else emptyList()) }


    }


}