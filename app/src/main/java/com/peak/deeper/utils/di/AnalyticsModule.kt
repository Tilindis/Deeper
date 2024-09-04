package com.peak.deeper.utils.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.peak.deeper.utils.firebase.FirebaseAnalyticsService
import com.peak.deeper.utils.firebase.FirebaseAnalyticsServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AnalyticsModule {
    @Singleton
    @Provides
    fun provideAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Singleton
    @Provides
    fun provideAppDataStore(firebaseAnalytics: FirebaseAnalytics): FirebaseAnalyticsService {
        return FirebaseAnalyticsServiceImpl(firebaseAnalytics)
    }
}
