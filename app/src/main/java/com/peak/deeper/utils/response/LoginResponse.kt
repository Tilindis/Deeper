package com.peak.deeper.utils.response

import com.squareup.moshi.Json

data class LoginResponse(
    @field:Json(name = "login")
    val login: LoginInfo? = null,

    @field:Json(name = "user")
    val user: User? = null,

    @field:Json(name = "scans")
    val scans: List<Scan>? = null,

    @field:Json(name = "makara")
    val makara: Makara? = null,
)
