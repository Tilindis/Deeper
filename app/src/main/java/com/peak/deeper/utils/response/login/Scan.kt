package com.peak.deeper.utils.response.login

import com.peak.deeper.utils.entity.ScanEntity
import com.squareup.moshi.Json
import java.time.LocalDateTime

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
    val date: LocalDateTime? = null,

    @field:Json(name = "scanPoints")
    val scanPoints: Int? = null,

    @field:Json(name = "mode")
    val mode: Int? = null,
) {
    fun toScanEntity(userId: Int?): ScanEntity {
        return ScanEntity(
            id = id ?: 0,
            userId = userId ?: 0,
            lat = lat ?: 0.0,
            lon = lon ?: 0.0,
            name = name ?: "",
            date = date ?: LocalDateTime.now(),
            scanPoints = scanPoints ?: 0,
            mode = mode ?: 0,
        )
    }
}
