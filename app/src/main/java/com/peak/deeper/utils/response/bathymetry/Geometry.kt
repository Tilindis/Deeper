package com.peak.deeper.utils.response.bathymetry

import com.peak.deeper.utils.domain.CoordinatesDomain
import com.squareup.moshi.Json

data class Geometry(
    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "bbox")
    val bbox: List<Double>? = null,

    @field:Json(name = "coordinates")
    val coordinates: List<List<List<Double>>>? = null,
) {
    fun toPolygons(): List<List<CoordinatesDomain>> {
        return coordinates?.map { polygon ->
            polygon.map { coordinates ->
                CoordinatesDomain(
                    longitude = coordinates[0],
                    latitude = coordinates[1],
                    depth = coordinates[2]
                )
            }
        } ?: emptyList()
    }
}
