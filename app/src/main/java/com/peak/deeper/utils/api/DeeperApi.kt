package com.peak.deeper.utils.api

import com.peak.deeper.utils.request.LoginRequest
import com.peak.deeper.utils.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeeperApi {
    @POST("login")
    suspend fun postLoginRequest(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
