package com.farzin.checklist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.checklist.repo.DataStoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repo:DataStoreRepositoryImpl) : ViewModel() {



    companion object {
        const val UID_KEY = "UID_KEY"
    }


    fun saveUID(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.putString(value, UID_KEY)
        }
    }

    fun getUID() : String? = runBlocking {
        repo.getString(UID_KEY)
    }



}