package com.peak.deeper.utils.interactor

import com.peak.deeper.utils.domain.CoordinatesDomain
import com.peak.deeper.utils.domain.ScanDomain
import com.peak.deeper.utils.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainInteractorImpl(
    private val mainRepository: MainRepository,
) : MainInteractor {
    override suspend fun getScansByUserId(userId: Int): Flow<List<ScanDomain>> {
        return mainRepository.getScansByUserId(userId).map { scanEntities ->
            scanEntities.map { scanEntity ->
                val scan = scanEntity.toScanDomain()
                val name = scan.name.ifBlank { "Scan(${scan.id})" }
                scan.copy(name = name)
            }
        }
    }

    override suspend fun getGeoData(scanIds: String, token: String): Flow<List<List<CoordinatesDomain>>?> {
        return mainRepository.getGeoData(scanIds, token)
    }
}
