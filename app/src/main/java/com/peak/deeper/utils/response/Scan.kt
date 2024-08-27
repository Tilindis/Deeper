package com.peak.deeper.utils.response

import com.squareup.moshi.Json

data class Scan(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "lat")
    val lat: Double? = null,

    @field:Json(name = "lon")
    val lon: Double? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "date")
    val date: String? = null,

    @field:Json(name = "scanPoints")
    val scanPoints: Int? = null,

    @field:Json(name = "mode")
    val mode: Int? = null,
)
