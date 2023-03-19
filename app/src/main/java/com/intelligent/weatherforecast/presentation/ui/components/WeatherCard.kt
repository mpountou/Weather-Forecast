package com.intelligent.weatherforecast.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intelligent.weatherforecast.R
import com.intelligent.weatherforecast.domain.weather.WeatherInfo
import com.intelligent.weatherforecast.presentation.getTime
import java.time.LocalDateTime
import kotlin.math.roundToInt


@Composable
fun WeatherCard(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .heightIn(300.dp)
        .padding(horizontal = 15.dp, vertical = 5.dp),
    cardContent: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        content = { cardContent() }
    )
}

@Composable
fun WeatherCardContent(
    weatherInfo: WeatherInfo
) {
    val currentWeather = weatherInfo.currentWeatherData
    val time = currentWeather?.time?.getTime() ?: LocalDateTime.now().getTime()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = "Today $time",
            style = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = MaterialTheme.typography.subtitle1.fontWeight
            )
        )
        Image(
            modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally).padding(10.dp),
            painter = painterResource(id = currentWeather!!.weatherType.iconRes),
            contentDescription = currentWeather.weatherType.weatherDesc
        )
        val celsiusDegrees = currentWeather.temperatureCelsius.toString()
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = " $celsiusDegrees \u2103",
            style = TextStyle(
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = MaterialTheme.typography.h3.fontWeight
            )
        )
        val weatherDesc = currentWeather.weatherType.weatherDesc
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = weatherDesc,
            style = TextStyle(
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = MaterialTheme.typography.h6.fontWeight
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherDataDisplay(
                value = currentWeather.pressure.roundToInt(),
                unit = "hpa",
                icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
            )
            WeatherDataDisplay(
                value = currentWeather.humidity.roundToInt(),
                unit = "%",
                icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
            )
            WeatherDataDisplay(
                value = currentWeather.windSpeed.roundToInt(),
                unit = "km/h",
                icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
            )
        }
    }
}