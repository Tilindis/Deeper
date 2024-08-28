package com.peak.deeper.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peak.deeper.utils.converter.database.DataBaseConverters
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.entity.ScanEntity

@Database(entities = [ScanEntity::class], version = 1)
@TypeConverters(DataBaseConverters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun scanDao(): ScanDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDataBase::class.java, "deeper.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDataBase
        }
    }
}
