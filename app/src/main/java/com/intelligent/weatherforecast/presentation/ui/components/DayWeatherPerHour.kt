package com.intelligent.weatherforecast.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.intelligent.weatherforecast.domain.weather.WeatherData
import com.intelligent.weatherforecast.presentation.getDateDDMMYYYY
import com.intelligent.weatherforecast.presentation.getTime

@Composable
fun DayWeatherPerHour(
    weatherData: List<WeatherData>
) {
    val time = weatherData[0].time
    val nameOfDay = time.dayOfWeek.name.toLowerCase().capitalize()
    Text(
        modifier = Modifier.padding(horizontal = 15.dp),
        text = nameOfDay+" "+time.getDateDDMMYYYY(),
        style = TextStyle(
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(weatherData) {
            WeatherPerHourItem(weatherData = it)
        }
    }
}

@Composable
fun WeatherPerHourItem(
    weatherData: WeatherData
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = weatherData.time.getTime())
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = weatherData.weatherType.weatherDesc
        )
        val celsius = weatherData.temperatureCelsius.toString()
        Text(text = "$celsius ℃")
    }
}
