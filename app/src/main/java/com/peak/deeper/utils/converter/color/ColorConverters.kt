package com.peak.deeper.utils.converter.color

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import com.peak.deeper.ui.theme.Water
import com.peak.deeper.ui.theme.WaterDeep

class ColorConverters {
    fun colorFromDepth(value: Double): Color {
        return when (value) {
            in 10.0..value -> WaterDeep
            in value..0.0 -> Water
            else -> {
                val normalizedValue = value / 10 // Set value between 0..1
                lerp(Water, WaterDeep, normalizedValue.toFloat())
            }
        }
    }
}
