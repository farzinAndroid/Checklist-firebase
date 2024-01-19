package com.farzin.checklist.repo

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.farzin.checklist.utils.Constants.DATA_STORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject


val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
class DataStoreRepositoryImpl @Inject constructor(private val context:Context) : DataStoreRepository {
    override suspend fun putString(value: String, key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }
}