package com.peak.deeper.utils.repository

import com.peak.deeper.utils.domain.CoordinatesDomain
import com.peak.deeper.utils.entity.ScanEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getScansByUserId(userId: Int): Flow<List<ScanEntity>>
    suspend fun getGeoData(scanIds: String, token: String): Flow<List<List<CoordinatesDomain>>?>
}
