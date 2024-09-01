package com.peak.deeper.utils.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.peak.deeper.R
import com.peak.deeper.feature.main.MainState

@Composable
fun MainDrawerContent(state: MainState, onScan: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            color = Color.White,
            text = stringResource(id = R.string.title_scans),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )

        LazyColumn {
            items(state.scans) { scan ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onScan(scan.id) },
                ) {
                    Image( // Possible to use coil or glide to load images, but API returns nulls for url.
                        painter = painterResource(id = R.drawable.ic_logo_deeper),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                    )

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(
                            text = scan.name,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Text(
                            color = Color.White,
                            text = with(scan.date) {
                                stringResource(
                                    id = R.string.date_time_remaining,
                                    year, monthValue, dayOfMonth, hour, minute
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
