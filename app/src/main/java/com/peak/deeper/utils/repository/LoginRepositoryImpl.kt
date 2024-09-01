package com.peak.deeper.utils.repository

import android.util.Log
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.datastore.DataStore
import com.peak.deeper.utils.request.LoginRequest
import java.util.UUID
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: DeeperApi,
    private val scanDao: ScanDao,
    private val dataStore: DataStore,
) : LoginRepository {
    override suspend fun requestLogin(email: String, password: String) {
        runCatching {
            api.postLoginRequest(LoginRequest(email, password)).body()
        }.onSuccess { loginResponse ->
            loginResponse?.let { response ->
                val (login, user, scans, makara) = response
                login?.let { loginData ->
                    with(loginData) {
                        userId?.let {
                            dataStore.saveUserIdValue(it)
                            dataStore.saveLoginPassedValue(true)
                            scans?.let { scansResponse ->
                                scanDao.insertScans(scansResponse.map { scan -> scan.toScanEntity(it) })
                            }
                        }
                        token?.let { dataStore.saveUserTokenValue(it) }
                        val appId = appId ?: UUID.randomUUID().toString()
                        // TODO Register app id in API
                        dataStore.saveAppIdValue(appId)
                    }
                }

                user?.let {
                    Log.i("LoginRepository", "$it - User Data")
                }

                makara?.let {
                    Log.i("LoginRepository", "$it - Makara Data")
                }
            }
        }.onFailure {
            Log.e("LoginRepository", "Deeper Api: ${it.message}")
        }

        // Todo add errors handling
    }
}
