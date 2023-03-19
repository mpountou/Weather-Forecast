package com.intelligent.weatherforecast.data.mappers

import com.intelligent.weatherforecast.data.remote.model.HourlyWeatherForecast
import com.intelligent.weatherforecast.data.remote.model.WeatherForecast
import com.intelligent.weatherforecast.domain.weather.WeatherData
import com.intelligent.weatherforecast.domain.weather.WeatherInfo
import com.intelligent.weatherforecast.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun HourlyWeatherForecast.toWeatherDataMap() : Map<Int,List<WeatherData>> {
    return timeList.mapIndexed { index, time ->
        val temperature = temperatureList[index]
        val weatherCode = weatherCodeList[index]
        val windSpeed = windSpeedList[index]
        val pressure = pressureList[index]
        val humidity = humidityList[index]
       IndexedWeatherData(
           index = index,
           data =  WeatherData(
               time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
               temperatureCelsius = temperature,
               pressure = pressure,
               windSpeed = windSpeed,
               humidity = humidity,
               weatherType = WeatherType.fromWMO(weatherCode)
           )
       )
    }.groupBy { it.index / 24 }.mapValues { it.value.map { it.data } }
}

fun WeatherForecast.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = hourlyWeatherForecast.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}