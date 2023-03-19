package com.intelligent.weatherforecast.data.remote.model

import com.squareup.moshi.Json

data class WeatherForecast(
    @field:Json(name = "hourly")
    val hourlyWeatherForecast: HourlyWeatherForecast
)
