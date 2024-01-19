package com.farzin.checklist.ui.screen.add_update

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.farzin.checklist.utils.DigitHelper
import com.farzin.checklist.viewModel.DataStoreViewModel
import com.farzin.checklist.viewModel.TaskViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddUpdateScreen(
    taskId: String,
    navController: NavController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
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

        stickyHeader {
            AddUpdateTopBar(
                titleText = titleText,
                onClick = { navController.popBackStack() })
        }

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
                textValue = DigitHelper.digitByLang(taskDueDateText),
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
                textValue = DigitHelper.digitByLang(taskDueTimeText),
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
                        if (taskTitleText.isEmpty() || taskDueDateText.isEmpty() || taskDueTimeText.isEmpty() ||
                            priorityNumber == -1 || subTasks.isEmpty() || taskDescriptionText.isEmpty()
                        ) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_fill_out_everything),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            taskViewModel.addTask(
                                userId = dataStoreViewModel.getUID().toString(),
                                dueTime = taskDueTimeText,
                                dueDate = taskDueDateText,
                                description = taskDescriptionText,
                                title = taskTitleText,
                                priority = priorityNumber,
                                subTasks = subTasks
                            )

                            scope.launch {
                                delay(300)
                                navController.popBackStack()
                            }
                        }

                    },
                    onClearClicked = {
                        taskTitleText = ""
                        taskDueDateText = ""
                        taskDueTimeText = ""
                        taskDescriptionText = ""
                    },
                    loadingState = taskViewModel.addTaskLoading
                )
            }
        } else {
            item {
                UpdateTaskButtonSection(
                    onEditClicked = {

                        if (taskTitleText.isEmpty() || taskDueDateText.isEmpty() || taskDueTimeText.isEmpty() ||
                            priorityNumber == -1 || subTasks.isEmpty() || taskDescriptionText.isEmpty()
                        ) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_fill_out_everything),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            val updatedTask = Task(
                                taskId = taskId,
                                priority = priorityNumber,
                                title = taskTitleText,
                                description = taskDescriptionText,
                                userId = dataStoreViewModel.getUID().toString(),
                                dueDate = taskDueDateText,
                                dueTime = taskDueTimeText,
                                subTask = subTasks,
                            )

                            taskViewModel.updateTask(
                                task = updatedTask
                            )

                            scope.launch {
                                delay(300)
                                navController.popBackStack()
                            }
                        }


                    },
                    onClearClicked = {
                        taskTitleText = ""
                        taskDueDateText = ""
                        taskDueTimeText = ""
                        taskDescriptionText = ""
                    },
                    loadingState = taskViewModel.updateTaskLoading
                )
            }
        }


    }


}