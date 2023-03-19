package com.intelligent.weatherforecast.di

import android.app.Application
import com.google.android.gms.location.LocationServices
import com.intelligent.weatherforecast.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://api.open-meteo.com/"

    @Provides
    @Singleton
    fun provideMoshi() = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(factory: MoshiConverterFactory) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory).build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application) =
        LocationServices.getFusedLocationProviderClient(app)
}