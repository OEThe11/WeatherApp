package com.example.weatherapp.network

import com.example.weatherapp.Constants.API_KEY
import com.example.weatherapp.model.GetWeatherNetworkResponse
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.network.Endpoints.DAILY_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(DAILY_ENDPOINT)
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "imperial"
    ): GetWeatherNetworkResponse
}