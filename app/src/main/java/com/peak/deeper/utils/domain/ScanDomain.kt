package com.peak.deeper.utils.domain

import com.peak.deeper.utils.models.ScanViewData
import java.time.LocalDateTime

data class ScanDomain(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val date: LocalDateTime,
    val scanPoints: Int,
    val mode: Int,
    val userId: Int,
) {
    fun toScanViewData(): ScanViewData {
        return ScanViewData(
            id = id,
            lat = lat,
            lon = lon,
            name = name,
            date = date,
        )
    }
}
