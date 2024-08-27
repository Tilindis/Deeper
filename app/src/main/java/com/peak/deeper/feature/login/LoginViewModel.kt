package com.peak.deeper.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peak.deeper.utils.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun updatePassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onLogin() {
        viewModelScope.launch {
            loginInteractor.requestLogin(
                email = _state.value.email,
                password = _state.value.password
            )
        }
    }
}
