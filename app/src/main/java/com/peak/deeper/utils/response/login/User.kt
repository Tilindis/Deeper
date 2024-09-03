package com.peak.deeper.utils.response.login

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "userId")
    val userId: Int? = null,

    @field:Json(name = "familyName")
    val familyName: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "email")
    val email: String? = null,

    @field:Json(name = "locale")
    val locale: String? = null,

    @field:Json(name = "subscribe")
    val subscribe: Boolean? = null,

    @field:Json(name = "image")
    val image: String? = null,

    @field:Json(name = "lastUsedDeeperModel")
    val lastUsedDeeperModel: String? = null,

    @field:Json(name = "strictMode")
    val strictMode: Boolean? = null,

    @field:Json(name = "country")
    val country: String? = null,
)
