package com.peak.deeper.utils.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peak.deeper.utils.entity.ScanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao {
    @Query("SELECT * FROM scan")
    fun getScans(): Flow<List<ScanEntity>>

    @Query("SELECT * FROM scan WHERE user_id IN (:userId)")
    fun getByUserId(userId: Int): Flow<List<ScanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScans(scanEntityList: List<ScanEntity>)
}
