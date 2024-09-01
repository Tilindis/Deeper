package com.peak.deeper.feature.main

import com.peak.deeper.utils.models.ScanViewData

data class MainState(
    val isLoading: Boolean = false,
    val scans: List<ScanViewData> = emptyList()
)
