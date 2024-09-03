package com.peak.deeper.utils.response.bathymetry

import com.squareup.moshi.Json

data class Feature(
    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "properties")
    val properties: Properties? = null,

    @field:Json(name = "geometry")
    val geometry: Geometry? = null,
)
