package com.peak.deeper.utils.api

import com.peak.deeper.utils.request.LoginRequest
import com.peak.deeper.utils.response.bathymetry.GeoDataResponse
import com.peak.deeper.utils.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeeperApi {
    @POST("login")
    suspend fun postLoginRequest(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("geoData")
    suspend fun getGeoData(
        @Query("grid") grid: String = "FAST",
        @Query("generator") generator: String = "BS",
        @Query("scanIds") scanIds: String,
        @Query("token") token: String
    ): Response<GeoDataResponse>
}
