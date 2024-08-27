package com.peak.deeper.utils.di

import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.constants.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideDeeperApiService(@Named(Constants.DEEPER_API_NAME) retrofit: Retrofit): DeeperApi =
        retrofit.create(DeeperApi::class.java)

    @Singleton
    @Provides
    @Named(Constants.DEEPER_API_NAME)
    fun provideRetrofitDeeper(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        createRetrofitBuilder(okHttpClient, moshi , Constants.DEEPER_URL)

    private fun createRetrofitBuilder(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        url: String
    ) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(url)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}
