package com.peak.deeper.utils.response.bathymetry

import com.squareup.moshi.Json

data class GeoDataResponse(
    @field:Json(name = "bathymetry")
    val bathymetry: Bathymetry? = null,
)
