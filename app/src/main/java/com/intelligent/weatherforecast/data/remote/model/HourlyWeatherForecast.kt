package com.intelligent.weatherforecast.data.remote.model

import com.squareup.moshi.Json

data class HourlyWeatherForecast(
    @field:Json(name = "time")
    val timeList: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatureList: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodeList: List<Int>,
    @field:Json(name = "relativehumidity_2m")
    val humidityList: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeedList: List<Double>,
    @field:Json(name = "pressure_msl")
    val pressureList: List<Double>
)
