package com.peak.deeper.utils.repository

import android.util.Log
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.request.LoginRequest
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: DeeperApi,
    private val scanDao: ScanDao,
) : LoginRepository {
    override suspend fun requestLogin(email: String, password: String) {
        runCatching {
            api.postLoginRequest(LoginRequest(email, password)).body()
        }.map { loginResponse ->
            loginResponse?.let { response ->
                val (login, user, scans, makara) = response
                user?.let {
                    Log.i("LoginRepository", "$it - User Data")
                }
                scans?.let {
                    Log.i("LoginRepository", "$it - Scans Data")
                }
                makara?.let {
                    Log.i("LoginRepository", "$it - Makara Data")
                }
                login?.let {
                    Log.i("LoginRepository", "$it - Login Data")
                    response.scans?.map {
                        scans -> scans.toScanEntity(it.userId)
                    }
                }
            }
        }.onSuccess { scans ->
            scans?.let {
                scanDao.insertScans(it)
            }
        }.onFailure {
            Log.e("LoginRepository", "Deeper Api: ${it.message}")
        }

        // Todo add errors handling
    }
}
