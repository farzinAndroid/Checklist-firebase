package com.farzin.checklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class TaskViewModel @Inject constructor(private val repo: FiresStoreRepository) : ViewModel() {

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
        isTaskCompleted: Boolean,
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
                isTaskCompleted = isTaskCompleted,
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


}