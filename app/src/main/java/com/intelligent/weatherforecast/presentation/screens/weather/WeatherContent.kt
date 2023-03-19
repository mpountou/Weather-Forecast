package com.intelligent.weatherforecast.presentation.screens.weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import com.intelligent.weatherforecast.R
import com.intelligent.weatherforecast.domain.util.RequestState
import com.intelligent.weatherforecast.domain.weather.WeatherInfo

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherContent(
    weatherRequest: RequestState<WeatherInfo>,
    onRetryClicked: () -> Unit,
    multiplePermissionState: MultiplePermissionsState
) {
    when (weatherRequest) {
        is RequestState.Success<WeatherInfo> -> {
            val weatherInfo = weatherRequest.data
            WeatherContentSuccess(
                weatherInfo = weatherInfo
            )
        }
        is RequestState.Error -> {
            WeatherContentError(
                message = stringResource(id = R.string.network_error),
                onRetryClicked = onRetryClicked
            )
        }
        is RequestState.Initial -> {
            WeatherContentInitial(
                message = stringResource(id = R.string.initial_message)
            )
        }
        is RequestState.Loading -> {
            WeatherContentLoading(
                message = stringResource(id = R.string.loading_message)
            )
        }
        is RequestState.LocationError -> {
            WeatherContentError(
                message = stringResource(id = R.string.location_error_message),
                onRetryClicked = {
                    multiplePermissionState.launchMultiplePermissionRequest()
                }
            )
        }
    }
}