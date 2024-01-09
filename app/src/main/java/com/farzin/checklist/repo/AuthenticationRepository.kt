package com.farzin.checklist.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val auth: FirebaseAuth
) {


    val currentUser: FirebaseUser? = auth.currentUser

    fun hasUser(): Boolean = auth.currentUser != null

    fun getUserId(): String = auth.currentUser?.uid.orEmpty()



    suspend fun createUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            auth
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
        withContext(Dispatchers.IO) {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    onSuccess(result.user?.uid!!)
                }
                .addOnFailureListener { result ->
                    onFailure(result.message!!)
                }
        }

    }


    suspend fun createUserWithGoogleAccount(
        tokenId: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        val firebaseCredential = GoogleAuthProvider.getCredential(tokenId, null)
        auth
            .signInWithCredential(firebaseCredential)
            .addOnSuccessListener { result ->
                onSuccess(result.user?.uid!!)
            }
            .addOnFailureListener { result ->
                onFailure(result.message!!)
            }
    }
}