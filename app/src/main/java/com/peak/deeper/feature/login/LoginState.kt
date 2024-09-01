package com.peak.deeper.feature.login

data class LoginState(
    val email: String = "deeperangler@gmail.com", // <-- Add test data,
    val password: String = "Deeper10899",         // <-- For faster checks.
    val isUserLogged: Boolean? = null
)
