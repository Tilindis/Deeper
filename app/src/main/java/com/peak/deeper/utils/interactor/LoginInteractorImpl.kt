package com.peak.deeper.utils.interactor

import com.peak.deeper.utils.repository.LoginRepository
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : LoginInteractor {
    override suspend fun requestLogin(email: String, password: String) {
        loginRepository.requestLogin(email, password) // Todo Add validation
    }
}
