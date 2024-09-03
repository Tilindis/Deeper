package com.peak.deeper.utils.response.login

import com.squareup.moshi.Json

data class Makara(
    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "user")
    val user: String? = null,

    @field:Json(name = "password")
    val password: String? = null,
)
