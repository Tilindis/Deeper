package com.peak.deeper.utils.repository

interface LoginRepository {
    suspend fun requestLogin(email: String, password: String)
}
