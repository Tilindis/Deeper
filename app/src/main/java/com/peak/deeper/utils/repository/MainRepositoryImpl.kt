package com.peak.deeper.utils.repository

import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.entity.ScanEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: DeeperApi,
    private val scanDao: ScanDao,
) : MainRepository {
    override suspend fun getScansByUserId(userId: Int): Flow<List<ScanEntity>> {
        return scanDao.getByUserId(userId)
    }
}
