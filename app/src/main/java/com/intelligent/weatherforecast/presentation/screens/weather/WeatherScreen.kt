package com.intelligent.weatherforecast.presentation.screens.weather

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.intelligent.weatherforecast.presentation.WeatherViewModel
import com.intelligent.weatherforecast.presentation.ui.components.WeatherTopAppBar

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {

    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val acceptedPerm = multiplePermissionState.permissions.filter { it.status.isGranted }.size

    val weatherRequest by viewModel.weatherInfo.collectAsState()
    Scaffold(
        topBar = { WeatherTopAppBar() },
        content = {
            WeatherContent(
                weatherRequest = weatherRequest,
                onRetryClicked = {
                    viewModel.loadWeatherInfo()
                },
                multiplePermissionState = multiplePermissionState
            )
        }
    )
    LaunchedEffect(key1 = acceptedPerm) {
        if(acceptedPerm==2){
            viewModel.loadWeatherInfo()
        }
    }
}