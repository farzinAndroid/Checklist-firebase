package com.farzin.checklist.repo

import com.farzin.checklist.model.AuthResult
import com.farzin.checklist.model.NetworkResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepository @Inject constructor() {


    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    suspend fun createUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            Firebase.auth
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    onSuccess()
                }
                .addOnFailureListener { result ->
                    onFailure(result.message!!)
                }
        }

    }


    suspend fun signInUser(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {result->
                onSuccess(result.user?.uid!!)
            }
            .addOnFailureListener { result->
                onFailure(result.message!!)
            }
    }
}