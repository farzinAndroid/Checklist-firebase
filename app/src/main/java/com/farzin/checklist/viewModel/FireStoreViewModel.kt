package com.farzin.checklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.checklist.model.NetworkResult
import com.farzin.checklist.model.Subtask
import com.farzin.checklist.model.Task
import com.farzin.checklist.repo.FiresStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireStoreViewModel @Inject constructor(private val repo: FiresStoreRepository) : ViewModel() {

    val tasks = repo.getUserTasks()


    var loading by mutableStateOf(false)
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

        loading = true
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
                    loading = false
                    addTaskMessage = it
                },
                onError = {
                    loading = false
                    addTaskMessage = it
                }
            )
        }
    }


}