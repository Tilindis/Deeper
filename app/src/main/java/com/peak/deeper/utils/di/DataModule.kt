package com.peak.deeper.utils.di

import com.peak.deeper.utils.api.DeeperApi
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
    fun provideLoginRepository(api: DeeperApi) : LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideLoginInteractor(loginRepository: LoginRepository): LoginInteractor {
        return LoginInteractorImpl(loginRepository)
    }
}
