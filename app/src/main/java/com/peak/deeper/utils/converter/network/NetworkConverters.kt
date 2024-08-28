package com.peak.deeper.utils.converter.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class NetworkConverters {
    @FromJson
    fun fromJson(value: String?): LocalDateTime? {
        value?.let {
            return try {
                LocalDateTime.ofInstant(Instant.parse(value), ZoneOffset.UTC)
            } catch (e: Exception) {
                null
            }
        } ?: return null
    }

    @ToJson
    fun toJson(value: LocalDateTime?): String? {
        return value?.let { it.toInstant(ZoneOffset.UTC)?.toString() }
    }
}
