package com.peak.deeper.utils.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peak.deeper.utils.domain.ScanDomain
import java.time.LocalDateTime

@Entity(tableName = "scan")
data class ScanEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "scan_points") val scanPoints: Int,
    @ColumnInfo(name = "mode") val mode: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
) {
    fun toScanDomain(): ScanDomain {
        return ScanDomain(
            id = id,
            lat = lat,
            lon = lon,
            name = name,
            date = date,
            scanPoints = scanPoints,
            mode = mode,
            userId = userId
        )
    }
}
