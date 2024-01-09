package com.farzin.checklist.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.checklist.model.Subtask
import com.farzin.checklist.model.Task
import com.farzin.checklist.ui.components.MyDividerHorizontal
import com.farzin.checklist.ui.theme.mainBackground
import com.farzin.checklist.utils.DigitHelper
import com.farzin.checklist.viewModel.AuthenticationViewModel
import com.farzin.checklist.viewModel.FireStoreViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    fireStoreViewModel: FireStoreViewModel = hiltViewModel(),
) {

    Home(authenticationViewModel,fireStoreViewModel, navController,)
}

@Composable
fun Home(
    authenticationViewModel: AuthenticationViewModel,
    fireStoreViewModel: FireStoreViewModel,
    navController: NavController
) {


    val subtaskList = listOf<Subtask>(
           Subtask(subtaskId = 1,title = "sub 1",isSubtaskCompleted = false),
           Subtask(subtaskId = 2,title = "sub 2",isSubtaskCompleted = false),
           Subtask(subtaskId = 3,title = "sub 3",isSubtaskCompleted = false),
       )

    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(true){
        fireStoreViewModel.tasks.collectLatest {result->
            tasks = result
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackground),
    ){

        TopBarSection(
            onCardClicked = {
                fireStoreViewModel.addTask(
                    userId = Firebase.auth.uid!!,
                    subTasks = subtaskList,
                    isTaskCompleted = false,
                    dueTime = "14:15:30",
                    dueDate = "19/10/1402",
                    priority = 2,
                    title = "Test task",
                    description = "this is a test"
                )
            }
        )
        MyDividerHorizontal()

        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(tasks){
                Text(text = DigitHelper.digitByLang(it.dueTime))
            }
        }

        Log.e("TAG",fireStoreViewModel.addTaskMessage)
//        Log.e("TAG",(Firebase.auth.currentUser != null).toString())

    }



}