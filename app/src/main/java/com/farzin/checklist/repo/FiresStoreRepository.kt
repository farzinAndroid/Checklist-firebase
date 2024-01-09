package com.farzin.checklist.repo

import com.farzin.checklist.model.NetworkResult
import com.farzin.checklist.model.Subtask
import com.farzin.checklist.model.Task
import com.farzin.checklist.utils.Constants.COLLECTION_ID
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FiresStoreRepository @Inject constructor(private val fireStore: FirebaseFirestore) {


    fun getUserTasks(): Flow<List<Task>> = callbackFlow {
        var snapShotListener: ListenerRegistration? = null

        try {
            snapShotListener = fireStore
                .collection(COLLECTION_ID)
                .addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null){
                        val tasks = snapShot.toObjects(Task::class.java)
                        NetworkResult.Success("success",tasks)
                    }else{
                        NetworkResult.Error(error?.message.toString())
                    }

                    response.data?.let { trySend(it) }
                }
        }catch (e:Exception){
            e.printStackTrace()
        }

        awaitClose { snapShotListener?.remove() }

    }

    fun getSingleTask(
        taskId:String,
        onError:(String)->Unit,
        onSuccess:(Task)->Unit
    ){
            fireStore
                .collection(COLLECTION_ID)
                .document(taskId)
                .get()
                .addOnSuccessListener {response->
                    response.toObject(Task::class.java)?.let { onSuccess(it) }
                }
                .addOnFailureListener {response->
                    response.message?.let { onError(it) }
                }
    }

    suspend fun addTask(
        userId: String,
        title: String,
        description: String,
        priority: Int,
        dueDate: String,
        dueTime: String,
        subTasks: List<Subtask>,
        isTaskCompleted: Boolean,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val taskId = fireStore.collection(COLLECTION_ID).document().id

        val task = Task(
            userId = userId,
            taskId = taskId,
            description = description,
            title = title,
            priority = priority,
            dueDate = dueDate,
            dueTime = dueTime,
            subTask = subTasks,
            isTaskCompleted = isTaskCompleted
        )

        withContext(Dispatchers.IO){
            fireStore.collection(COLLECTION_ID)
                .document(taskId)
                .set(task)
                .addOnSuccessListener { response->
                    onSuccess("added successfully")
                }
                .addOnFailureListener {response->
                    response.message?.let { onError(it) }
                }
        }
    }
}