package com.farzin.checklist.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.model.home.Subtask
import com.farzin.checklist.model.home.Task
import com.farzin.checklist.navGraph.Screens
import com.farzin.checklist.ui.components.AddButton
import com.farzin.checklist.ui.components.MyDividerHorizontal
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

    Home(authenticationViewModel,taskViewModel, navController,)
}

@Composable
fun Home(
    authenticationViewModel: AuthenticationViewModel,
    taskViewModel: TaskViewModel,
    navController: NavController
) {

    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(true){
        taskViewModel.tasks.collectLatest { result->
            tasks = result
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.mainBackground),
        ){

            TopBarSection(
                onCardClicked = {
                    /*taskViewModel.addTask(
                        userId = Firebase.auth.uid!!,
                        subTasks = subtaskList,
                        isTaskCompleted = false,
                        dueTime = "14:15:30",
                        dueDate = "19/10/1402",
                        priority = 3,
                        title = "وظیفه تست",
                        description = "این تست است"
                    )*/
                }
            )
            MyDividerHorizontal()

            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(tasks){
                    TaskItem(
                       task =  it,
                        onCardClicked = {
                            navController.navigate(Screens.AddUpdateScreen.route + "?taskId=${it.taskId}")
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