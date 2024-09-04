package com.peak.deeper.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.peak.deeper.utils.datastore.DataStore
import com.peak.deeper.utils.firebase.FirebaseAnalyticsService
import com.peak.deeper.utils.interactor.MainInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val dataStore: DataStore,
    private val firebaseAnalyticsService: FirebaseAnalyticsService
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            dataStore.getUserIdValue().collect { userId ->
                userId?.let {
                    mainInteractor.getScansByUserId(it).collect { scans ->
                        _state.update { state ->
                            state.copy(scans = scans.map { scan -> scan.toScanViewData() })
                        }
                    }
                }
            }
        }
    }

    fun getPolygons(polygonsBox: Pair<Int, LatLng>) {
        viewModelScope.launch {
            dataStore.getUserTokenValue().collect { token ->
                token?.let {
                    mainInteractor.getGeoData(polygonsBox.first.toString(), it).collect { polygons ->
                        polygons?.let {
                            val polygonList = polygons.map { polygon ->
                                polygon.map { coordinates ->
                                    coordinates.toPolygonViewData()
                                }
                            }

                            _state.update {
                                state -> state.copy(
                                    polygonList = polygonList,
                                    currentPolygonsLocation = polygonsBox.second
                                )
                            }
                        }
                    }
                }
            }

            dataStore.getUserIdValue().collect { userId ->
                firebaseAnalyticsService.onScanDisplayed(polygonsBox.first, userId)
            }
        }
    }
}
