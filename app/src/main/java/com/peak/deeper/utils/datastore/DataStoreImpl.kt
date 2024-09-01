package com.peak.deeper.utils.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.peak.deeper.utils.constants.Constants.DATASTORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

import com.peak.deeper.utils.datastore.DataStore as AppDataStore

class DataStoreImpl @Inject constructor(@ApplicationContext val context: Context) : AppDataStore {
    override fun getLoginPassedValue(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_USER_LOGGED_IN_KEY] ?: false
        }

    override suspend fun saveLoginPassedValue(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN_KEY] = status
        }
    }

    override fun getUserIdValue(): Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY]
        }

    override suspend fun saveUserIdValue(status: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = status
        }
    }

    override fun getUserTokenValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY]
        }

    override suspend fun saveUserTokenValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = status
        }
    }

    override fun getAppIdValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[APP_ID_KEY]
        }

    override suspend fun saveAppIdValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[APP_ID_KEY] = status
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)
        private val IS_USER_LOGGED_IN_KEY = booleanPreferencesKey("key.is_user_logged_in")
        private val USER_ID_KEY = intPreferencesKey("key.user_id")
        private val USER_TOKEN_KEY = stringPreferencesKey("key.user_token")
        private val APP_ID_KEY = stringPreferencesKey("key.app_id")
    }
}
