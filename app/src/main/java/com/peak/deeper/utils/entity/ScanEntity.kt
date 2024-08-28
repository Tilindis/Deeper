package com.peak.deeper.utils.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
)
