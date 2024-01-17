package com.farzin.checklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picker.view.PersianBirthDatePicker
import com.example.picker.view.PersianExpirationDatePicker
import com.farzin.checklist.model.home.Subtask
import com.farzin.checklist.model.home.Task
import com.farzin.checklist.repo.FiresStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repo: FiresStoreRepository,
) : ViewModel() {

    val tasks = repo.getUserTasks()



    var addTaskLoading by mutableStateOf(false)
    var addTaskMessage by mutableStateOf("")
    fun addTask(
        userId: String,
        title: String,
        description: String,
        priority: Int,
        dueDate: String,
        dueTime: String,
        subTasks: List<Subtask>,
    ) {

        addTaskLoading = true
        viewModelScope.launch {
            repo.addTask(
                userId = userId,
                description = description,
                title = title,
                priority = priority,
                dueDate = dueDate,
                dueTime = dueTime,
                subTasks = subTasks,
                onSuccess = {
                    addTaskLoading = false
                    addTaskMessage = it
                },
                onError = {
                    addTaskLoading = false
                    addTaskMessage = it
                }
            )
        }
    }

    var singleTaskLoading by mutableStateOf(false)
    var singleTaskMessage by mutableStateOf("")
    val singleTask = MutableStateFlow(Task())

    fun getSingleTask(
        taskId: String,
    ) {
        singleTaskLoading = true
        repo.getSingleTask(
            taskId = taskId,
            onSuccess = { response ->
                singleTaskLoading = false
                singleTask.value = response

            },
            onError = { response ->
                singleTaskLoading = false
                singleTaskMessage = response
            }
        )
    }


    var updateTaskLoading by mutableStateOf(false)
    var updateTaskMessage by mutableStateOf("")
    fun updateTask(task:Task){
        updateTaskLoading = true

            repo.updateTask(
                task = task,
                onSuccess = {
                    updateTaskLoading = false
                    updateTaskMessage = it
                },
                onError = {
                    updateTaskLoading = false
                    updateTaskMessage = it
                }
            )

    }


}