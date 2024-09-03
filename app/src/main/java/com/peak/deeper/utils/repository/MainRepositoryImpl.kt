package com.peak.deeper.utils.repository

import android.util.Log
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.domain.CoordinatesDomain
import com.peak.deeper.utils.entity.ScanEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: DeeperApi,
    private val scanDao: ScanDao,
) : MainRepository {
    override suspend fun getScansByUserId(userId: Int): Flow<List<ScanEntity>> {
        return scanDao.getByUserId(userId)
    }

    override suspend fun getGeoData(scanIds: String, token: String): Flow<List<List<CoordinatesDomain>>?> = flow {
        runCatching {
            api.getGeoData(scanIds = scanIds, token = token).body()
        }.onSuccess { geoDataResponse ->
            val polygons = geoDataResponse?.bathymetry?.features?.flatMap { feature ->
                feature.geometry?.toPolygons() ?: emptyList()
            }
            emit(polygons)
        }.onFailure {
            Log.e("MainRepository","Deeper Api: ${it.message}")
            emit(null)
        }
    }
}
