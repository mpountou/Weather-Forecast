package com.intelligent.weatherforecast.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.intelligent.weatherforecast.R

@Composable
fun WeatherTopAppBar(title: String = stringResource(id = R.string.app_name)) {
    TopAppBar(
        title = { Text(text = title)}
    )
}