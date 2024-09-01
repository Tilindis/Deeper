package com.peak.deeper.utils.interactor

import com.peak.deeper.utils.domain.ScanDomain
import kotlinx.coroutines.flow.Flow

interface MainInteractor {
    suspend fun getScansByUserId(userId: Int): Flow<List<ScanDomain>>
}
