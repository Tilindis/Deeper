package com.peak.deeper.utils.response.bathymetry

import com.squareup.moshi.Json

data class Bathymetry(
    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "bbox")
    val bbox: List<Double>? = null,

    @field:Json(name = "features")
    val features: List<Feature>? = null,
)
