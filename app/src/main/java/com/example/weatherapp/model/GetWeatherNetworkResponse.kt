package com.example.weatherapp.model

data class GetWeatherNetworkResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherObject>,
    val message: Double
)