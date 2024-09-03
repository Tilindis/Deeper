package com.peak.deeper.utils.response.bathymetry

import com.squareup.moshi.Json

data class Properties(
    @field:Json(name = "depth")
    val depth: Double? = null,

    @field:Json(name = "id")
    val id: String? = null,
)
