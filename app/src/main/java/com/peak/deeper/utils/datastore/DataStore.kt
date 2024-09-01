package com.peak.deeper.utils.datastore

import kotlinx.coroutines.flow.Flow

interface DataStore {
    fun getLoginPassedValue(): Flow<Boolean>
    suspend fun saveLoginPassedValue(status: Boolean)

    fun getUserIdValue(): Flow<Int?>
    suspend fun saveUserIdValue(status: Int)

    fun getUserTokenValue(): Flow<String?>
    suspend fun saveUserTokenValue(status: String)

    fun getAppIdValue(): Flow<String?>
    suspend fun saveAppIdValue(status: String)
}
