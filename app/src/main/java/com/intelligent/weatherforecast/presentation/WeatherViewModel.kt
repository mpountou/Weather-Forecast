package com.intelligent.weatherforecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.weatherforecast.domain.location.LocationTracker
import com.intelligent.weatherforecast.domain.repository.WeatherRepository
import com.intelligent.weatherforecast.domain.util.RequestState
import com.intelligent.weatherforecast.domain.weather.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository : WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    private var _weatherInfo: MutableStateFlow<RequestState<WeatherInfo>> = MutableStateFlow(RequestState.Initial)
    val weatherInfo : StateFlow<RequestState<WeatherInfo>> = _weatherInfo

    fun loadWeatherInfo(){
        viewModelScope.launch {
            _weatherInfo.value = RequestState.Loading
            try {
                val location = locationTracker.getCurrentLocation() ?: throw Exception("Location Error")
                val data = repository.getWeatherForecast(location.latitude,location.longitude)
                _weatherInfo.value = RequestState.Success(data = data)
            } catch (e: Exception) {
                val message = e.message ?: "Error"
                _weatherInfo.value = if(message == "Location Error") RequestState.LocationError else RequestState.Error(error = message)
            }
        }
    }
}