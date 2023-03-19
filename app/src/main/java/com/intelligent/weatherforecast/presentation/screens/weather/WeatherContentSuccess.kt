package com.intelligent.weatherforecast.presentation.screens.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.intelligent.weatherforecast.domain.weather.WeatherInfo
import com.intelligent.weatherforecast.presentation.ui.components.DayWeatherPerHour
import com.intelligent.weatherforecast.presentation.ui.components.WeatherCard
import com.intelligent.weatherforecast.presentation.ui.components.WeatherCardContent

@Composable
fun WeatherContentSuccess(
    weatherInfo: WeatherInfo
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        WeatherCard(
            cardContent = { WeatherCardContent(weatherInfo = weatherInfo) }
        )
        LazyColumn {
            val totalWeatherForecastDays = weatherInfo.weatherDataPerDay.values.size
            items(totalWeatherForecastDays) {
                val weatherDayForecast = weatherInfo.weatherDataPerDay[it]!!
                DayWeatherPerHour(weatherData = weatherDayForecast)
            }
        }
    }
}