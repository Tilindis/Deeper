package com.peak.deeper.utils.di

import android.app.Application
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.dao.ScanDao
import com.peak.deeper.utils.database.AppDataBase
import com.peak.deeper.utils.datastore.DataStore
import com.peak.deeper.utils.datastore.DataStoreImpl
import com.peak.deeper.utils.interactor.LoginInteractor
import com.peak.deeper.utils.interactor.LoginInteractorImpl
import com.peak.deeper.utils.interactor.MainInteractor
import com.peak.deeper.utils.interactor.MainInteractorImpl
import com.peak.deeper.utils.repository.LoginRepository
import com.peak.deeper.utils.repository.LoginRepositoryImpl
import com.peak.deeper.utils.repository.MainRepository
import com.peak.deeper.utils.repository.MainRepositoryImpl
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
    fun provideAppDataStore(app: Application): DataStore {
        return DataStoreImpl(app)
    }

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
    fun provideLoginRepository(api: DeeperApi, scanDao: ScanDao, dataStore: DataStore) : LoginRepository {
        return LoginRepositoryImpl(api, scanDao, dataStore)
    }

    @Singleton
    @Provides
    fun provideLoginInteractor(loginRepository: LoginRepository): LoginInteractor {
        return LoginInteractorImpl(loginRepository)
    }

    @Singleton
    @Provides
    fun provideMainRepository(api: DeeperApi, scanDao: ScanDao) : MainRepository {
        return MainRepositoryImpl(api, scanDao)
    }

    @Singleton
    @Provides
    fun provideMainInteractor(mainRepository: MainRepository): MainInteractor {
        return MainInteractorImpl(mainRepository)
    }
}
