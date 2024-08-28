package com.peak.deeper.utils.converter.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class DataBaseConverters {
    @TypeConverter
    fun localDateTimeFromLong(value: Long?): LocalDateTime? = value?.let {
        LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC)
    }

    @TypeConverter
    fun localDateTimeToLong(date: LocalDateTime?): Long? =
        date?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
}
