package com.farzin.checklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.checklist.model.AuthResult
import com.farzin.checklist.model.NetworkResult
import com.farzin.checklist.repo.AuthenticationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repo: AuthenticationRepository) :
    ViewModel() {


    val currentUser = repo.currentUser

    var isCreateUserSuccess by mutableStateOf(false)
    var createUserError by mutableStateOf("")
    var createUserLoading by mutableStateOf(false)

    fun createUser(email: String, password: String,onSuccess:()->Unit) {
        createUserLoading = true
        viewModelScope.launch {
            repo.createUser(
                email = email,
                password = password,
                onSuccess = {
                    createUserLoading = false
                    isCreateUserSuccess = true
                    onSuccess()
                },
                onFailure = {message->
                    createUserLoading = false
                    isCreateUserSuccess = false
                    createUserError = message
                }
            )
        }
    }


    var isSignInSuccess by mutableStateOf(false)
    var signInError by mutableStateOf("")
    var signInLoading by mutableStateOf(false)

    fun signInUser(email: String, password: String,onSuccess:()->Unit) {
        signInLoading = true
        viewModelScope.launch(Dispatchers.IO) {
           repo.signInUser(
               email = email,
               password = password,
               onSuccess = { id->
                   signInLoading = false
                   isSignInSuccess = true
                   onSuccess()
               },
               onFailure = { message->
                   signInLoading = false
                   signInError = message
                   isSignInSuccess = false
               }
           )
        }
    }


    fun hasUser(): Boolean = repo.hasUser()

    fun getUserId() = repo.getUserId()


}