package com.peak.deeper.utils.di

import android.app.Application
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.database.AppDataBase
import com.peak.deeper.utils.interactor.LoginInteractor
import com.peak.deeper.utils.interactor.LoginInteractorImpl
import com.peak.deeper.utils.repository.LoginRepository
import com.peak.deeper.utils.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDataBase {
        return AppDataBase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideScanDao(appDataBase: AppDataBase) : ScanDao {
        return appDataBase.scanDao()
    }

    @Singleton
    @Provides
    fun provideLoginRepository(api: DeeperApi, scanDao: ScanDao) : LoginRepository {
        return LoginRepositoryImpl(api, scanDao)
    }

    @Singleton
    @Provides
    fun provideLoginInteractor(loginRepository: LoginRepository): LoginInteractor {
        return LoginInteractorImpl(loginRepository)
    }
}
