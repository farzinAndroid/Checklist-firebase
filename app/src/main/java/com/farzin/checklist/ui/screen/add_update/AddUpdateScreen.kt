package com.farzin.checklist.ui.screen.add_update

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateScreen(
    taskId: String,
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var singleTask by remember { mutableStateOf(Task()) }
    if (taskId.isNotEmpty()) {
        LaunchedEffect(taskId) {
            taskViewModel.getSingleTask(taskId)

            taskViewModel.singleTask.collectLatest { result ->
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
    var taskDescriptionText by remember { mutableStateOf("") }
    singleTask.let {
        taskDescriptionText = it.description
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


    val clockState = rememberUseCaseState()

    TimePickerComposable(
        onTimePicked = { hour, minute, second ->
            taskDueTimeText = "$hour:$minute:$second"
        },
        clockState = clockState
    )


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
                onClick = {}
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueDateText,
                onTextValueChanged = {},
                title = stringResource(R.string.due_date),
                icon = painterResource(R.drawable.calendar),
                onClick = {
                    datePicker(context) {
                        taskDueDateText = it
                    }
                }
            )
        }

        item {
            TaskTitleSection(
                textValue = taskDueTimeText,
                onTextValueChanged = {},
                title = stringResource(R.string.due_time),
                icon = painterResource(R.drawable.clock),
                onClick = {
                    clockState.show()
                }
            )
        }

        item {
            PrioritySection(
                priorityNumber = if (taskId.isNotEmpty()) priorityNumber else -1,
                priorityCallback = {
                    priorityNumber = it
                }
            )
        }


        item {
            SubTaskSection(
                subtask = if (taskId.isNotEmpty()) subTasks else emptyList(),
                subtaskCallback = {
                    subTasks = it
                    Log.e("TAG", subTasks.toString())
                }
            )
        }

        item {
            DescriptionSection(
                textValue = taskDescriptionText,
                onTextValueChanged = {
                    taskDescriptionText = it
                },
                icon = null,
                title = stringResource(R.string.description),
                onClick = {}
            )
        }

        if (taskId.isEmpty()) {
            item {
                AddTaskButtonSection(
                    onAddClicked = {
                        taskViewModel.addTask(
                            userId = Firebase.auth.currentUser?.uid!!,
                            dueTime = taskDueTimeText,
                            dueDate = taskDueDateText,
                            description = taskDescriptionText,
                            title = taskTitleText,
                            priority = priorityNumber,
                            subTasks = subTasks
                        )
                    },
                    onClearClicked = {

                    }
                )
            }
        } else {
            item {
                UpdateTaskButtonSection(
                    onEditClicked = {

                        val updatedTask = Task(
                            taskId = taskId,
                            priority = priorityNumber,
                            title = taskTitleText,
                            description = taskDescriptionText,
                            userId = Firebase.auth.currentUser?.uid!!,
                            dueDate = taskDueDateText,
                            dueTime = taskDueTimeText,
                            subTask = subTasks,
                        )

                        taskViewModel.updateTask(
                            task = updatedTask
                        )

                    },
                    onClearClicked = {

                    }
                )
            }
        }


    }


}