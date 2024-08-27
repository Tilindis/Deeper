package com.peak.deeper.utils.response

import com.squareup.moshi.Json

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
    val validTill: String? = null,

    @field:Json(name = "registrationDate")
    val registrationDate: String? = null,
)
