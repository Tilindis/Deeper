package com.peak.deeper.utils.interactor

interface LoginInteractor {
    suspend fun requestLogin(email: String, password: String)
}
