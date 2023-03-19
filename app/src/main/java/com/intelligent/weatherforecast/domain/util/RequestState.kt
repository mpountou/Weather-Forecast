package com.intelligent.weatherforecast.domain.util

sealed class RequestState<out T> {
    object Initial : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: String) : RequestState<Nothing>()
    object LocationError: RequestState<Nothing>()
}