package com.peak.deeper.utils.domain

import com.peak.deeper.utils.models.CoordinatesViewData

data class CoordinatesDomain(
    val latitude: Double?,
    val longitude: Double?,
    val depth: Double?,
) {
    fun toPolygonViewData(): CoordinatesViewData {
        return CoordinatesViewData(
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            depth = depth ?: 0.0,
        )
    }
}
