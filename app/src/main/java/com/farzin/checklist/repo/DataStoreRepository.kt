package com.farzin.checklist.repo

interface DataStoreRepository {

    suspend fun putString(value:String,key:String)
    suspend fun getString(key: String):String?

}