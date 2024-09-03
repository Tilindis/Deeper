package com.peak.deeper.utils.response.login

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class LoginInfo(
    @field:Json(name = "appId")
    val appId: String? = null,

    @field:Json(name = "token")
    val token: String? = null,

    @field:Json(name = "userId")
    val userId: Int? = null,

    @field:Json(name = "validated")
    val validated: Boolean? = null,

    @field:Json(name = "validTill")
    val validTill: LocalDateTime? = null,

    @field:Json(name = "registrationDate")
    val registrationDate: LocalDateTime? = null,
)
