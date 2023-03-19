package com.intelligent.weatherforecast.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.getTime() : String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(this)
}

fun LocalDateTime.getDateDDMMYYYY() : String{
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY")
    return formatter.format(this)
}