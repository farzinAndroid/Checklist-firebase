package com.farzin.checklist.model

data class AuthResult(
    val isSuccess:Boolean = false,
    val signInMethod:String = "",
    val uid:String = "",
    val email:String = ""
)
