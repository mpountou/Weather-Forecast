package com.intelligent.weatherforecast.data.repository

import com.intelligent.weatherforecast.data.mappers.toWeatherInfo
import com.intelligent.weatherforecast.data.remote.WeatherApi
import com.intelligent.weatherforecast.domain.repository.WeatherRepository
import com.intelligent.weatherforecast.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository{
    override suspend fun getWeatherForecast(lat: Double, long: Double): WeatherInfo {
        return weatherApi.getWeatherForecast(lat, long).toWeatherInfo()
    }
}