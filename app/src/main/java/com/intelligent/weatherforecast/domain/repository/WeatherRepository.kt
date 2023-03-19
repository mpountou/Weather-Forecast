package com.intelligent.weatherforecast.domain.repository

import com.intelligent.weatherforecast.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherForecast(lat:Double,long:Double): WeatherInfo
}