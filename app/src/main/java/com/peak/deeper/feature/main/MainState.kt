package com.peak.deeper.feature.main

import com.google.android.gms.maps.model.LatLng
import com.peak.deeper.utils.models.CoordinatesViewData
import com.peak.deeper.utils.models.ScanViewData

data class MainState(
    val isLoading: Boolean = false,
    val scans: List<ScanViewData> = emptyList(),
    val polygonList:  List<List<CoordinatesViewData>>? = emptyList(),
    val currentPolygonsLocation: LatLng? = null
)
