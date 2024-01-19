package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.model.home.Task
import com.farzin.checklist.navGraph.Screens
import com.farzin.checklist.ui.components.AddButton
import com.farzin.checklist.ui.components.MyDividerHorizontal
import com.farzin.checklist.ui.theme.darkText
import com.farzin.checklist.ui.theme.highPriority
import com.farzin.checklist.ui.theme.lowPriority
import com.farzin.checklist.ui.theme.mainBackground
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.farzin.checklist.viewModel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel(),
) {

    Home(authenticationViewModel, taskViewModel, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    authenticationViewModel: AuthenticationViewModel,
    taskViewModel: TaskViewModel,
    navController: NavController,
) {

    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        taskViewModel.tasks.collectLatest { result ->
            tasks = result
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.mainBackground),
        ) {

            TopBarSection(
                onCardClicked = {

                }
            )
            MyDividerHorizontal()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                state = rememberLazyListState(),
            ) {
                items(tasks.reversed(), key = { it.taskId }) { task ->

                    val swipeToDismissState = rememberDismissState(
                        confirmValueChange = { it ->
                            if (it == DismissValue.DismissedToStart) {
                                val updatedTask = task.copy(subTask = task.subTask.map {
                                    it.copy(subtaskCompleted = true)
                                })
                                taskViewModel.updateTask(updatedTask)
                                return@rememberDismissState false // Prevent dismissal

                            }

                            if (it == DismissValue.DismissedToEnd) {
                                taskViewModel.deleteTask(task)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = swipeToDismissState,
                        background = {
                            val color = when (swipeToDismissState.dismissDirection) {
                                DismissDirection.EndToStart -> {
                                    MaterialTheme.colorScheme.lowPriority
                                }

                                DismissDirection.StartToEnd -> {
                                    MaterialTheme.colorScheme.highPriority
                                }

                                else -> {
                                    Color.Gray
                                }
                            }


                            Box(
                                modifier = Modifier
                                    .padding(vertical = 18.dp)
                                    .fillMaxWidth()
                                    .height(90.dp)
                                    .padding(horizontal = 16.dp)
                                    .shadow(
                                        6.dp,
                                        shape = Shapes().extraLarge,
                                        spotColor = MaterialTheme.colorScheme.darkText
                                    )
                                    .background(color),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .padding(start = 8.dp)
                                )


                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(end = 8.dp)
                                )
                            }
                        },
                        dismissContent = {
                            TaskItem(
                                task = task,
                                onCardClicked = {
                                    navController.navigate(Screens.AddUpdateScreen.route + "?taskId=${task.taskId}")
                                }
                            )
                        }
                    )

                }
            }

        }


        AddButton(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .height(40.dp)
                .width(200.dp),
            onClick = {
                navController.navigate(Screens.AddUpdateScreen.route)
            }
        )

    }


}