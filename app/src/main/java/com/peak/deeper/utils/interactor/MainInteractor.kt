package com.peak.deeper.utils.interactor

import com.peak.deeper.utils.domain.CoordinatesDomain
import com.peak.deeper.utils.domain.ScanDomain
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    suspend fun getScansByUserId(userId: Int): Flow<List<ScanDomain>>
    suspend fun getGeoData(scanIds: String, token: String): Flow<List<List<CoordinatesDomain>>?>
}
