package com.peak.deeper.utils.models

import java.time.LocalDateTime

data class ScanViewData(
    val id: Int = 0,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val name: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
)
