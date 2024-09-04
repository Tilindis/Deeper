package com.peak.deeper.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.peak.deeper.DispatcherRule
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.database.AppDataBase
import com.peak.deeper.utils.entity.ScanEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDateTime

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DatabaseTest {
    private lateinit var database: AppDataBase
    private lateinit var scanDao: ScanDao

    private fun prepareTestData() = listOf (
        ScanEntity(id = 0, userId = 222, lat = 0.0, lon = 0.0, name = "", date = LocalDateTime.now(), scanPoints = 0, mode = 0),
        ScanEntity(id = 1, userId = 0, lat = 0.0, lon = 0.0, name = "", date = LocalDateTime.now(), scanPoints = 0, mode = 0),
        ScanEntity(id = 2, userId = 1, lat = 0.0, lon = 0.0, name = "", date = LocalDateTime.now(), scanPoints = 0, mode = 0),
        ScanEntity(id = 3, userId = 2, lat = 0.0, lon = 0.0, name = "", date = LocalDateTime.now(), scanPoints = 0, mode = 0),
        ScanEntity(id = 4, userId = 3, lat = 0.0, lon = 0.0, name = "", date = LocalDateTime.now(), scanPoints = 0, mode = 0)
    )

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        scanDao = database.scanDao()
    }

    @Test
    fun testInsertAndRetrieveScans() = runTest {
        scanDao.insertScans(prepareTestData())

        val scansFlow = scanDao.getScans()
        val scans = scansFlow.first()

        assertEquals(5, scans.size)
    }

    @Test
    fun testInsertAndGetScansByUserId() = runTest {
        scanDao.insertScans(prepareTestData())

        val user1ScansFlow = scanDao.getByUserId(222)
        val user1Scans = user1ScansFlow.first()

        assertEquals(user1Scans.size, 1)
        assertEquals(user1Scans[0].userId, prepareTestData().first().userId)
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}
