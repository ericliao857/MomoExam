package com.example.momoexam.di

import com.example.momoexam.data.OpenDataRepository
import com.example.momoexam.data.OpenDataRepositoryImpl
import com.example.momoexam.data.api.ApiService
import com.example.momoexam.data.network.NetworkDataSource
import com.example.momoexam.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindOpenDataRepository(repository: OpenDataRepositoryImpl): OpenDataRepository

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: NetworkDataSourceImpl): NetworkDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Singleton
    @Provides
    fun providerOkHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        )
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}