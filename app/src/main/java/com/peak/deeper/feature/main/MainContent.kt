package com.peak.deeper.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*

@Composable
fun MainContent(
    state: MainState,
    onOpenDrawer: () -> Unit,
) {
    val middleOfLithuania = LatLng(54.898521, 23.903597)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(middleOfLithuania, 5.5f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
        )

        IconButton(
            onClick = onOpenDrawer,
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 16.dp)
                .clip(CircleShape)
                .shadow(elevation = 4.dp)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
            )
        }
    }
}
