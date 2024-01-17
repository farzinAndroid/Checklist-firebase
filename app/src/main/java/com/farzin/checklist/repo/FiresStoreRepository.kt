package com.farzin.checklist.repo

import android.util.Log
import com.farzin.checklist.model.NetworkResult
import com.farzin.checklist.model.home.Subtask
import com.farzin.checklist.model.home.Task
import com.farzin.checklist.utils.Constants.COLLECTION_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FiresStoreRepository @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebase: FirebaseAuth,
) {


    fun getUserTasks(): Flow<List<Task>> = callbackFlow {
        var snapShotListener: ListenerRegistration? = null

        try {
            snapShotListener = fireStore
                .collection(COLLECTION_ID)
                .whereEqualTo("userId", firebase.currentUser?.uid)
                .addSnapshotListener { snapShot, error ->
                    val response = if (snapShot != null) {
                        val tasks = snapShot.toObjects(Task::class.java)
                        NetworkResult.Success("success", tasks)
                    } else {
                        Log.e("TAG", error?.message.toString())
                        NetworkResult.Error(error?.message.toString())
                    }

                    response.data?.let { trySend(it) }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        awaitClose { snapShotListener?.remove() }

    }

    fun getSingleTask(
        taskId: String,
        onError: (String) -> Unit,
        onSuccess: (Task) -> Unit,
    ) {
        fireStore
            .collection(COLLECTION_ID)
            .document(taskId)
            .get()
            .addOnSuccessListener { response ->
                response.toObject(Task::class.java)?.let { onSuccess(it) }
            }
            .addOnFailureListener { response ->
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
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
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
        )

        withContext(Dispatchers.IO) {
            fireStore.collection(COLLECTION_ID)
                .document(taskId)
                .set(task)
                .addOnSuccessListener { response ->
                    onSuccess("added successfully")
                }
                .addOnFailureListener { response ->
                    response.message?.let { onError(it) }
                }
        }
    }


    fun updateTask(
        task: Task,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {

        val updateData = hashMapOf<String, Any>(
            "userId" to task.userId,
            "title" to task.title,
            "description" to task.description,
            "priority" to task.priority,
            "dueDate" to task.dueDate,
            "dueTime" to task.dueTime,
            "subTask" to task.subTask,
        )


            fireStore.collection(COLLECTION_ID)
                .document(task.taskId)
                .update(updateData)
                .addOnSuccessListener { result ->
                    onSuccess("updated successfully")
                }
                .addOnFailureListener { result ->
                    onError(result.message!!)
                }



    }

}