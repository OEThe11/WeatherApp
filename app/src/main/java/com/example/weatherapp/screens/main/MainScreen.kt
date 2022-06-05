package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.GetWeatherNetworkResponse
import com.example.weatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()){


    val weatherData = produceState<DataOrException<GetWeatherNetworkResponse, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = viewModel.getWeatherData(city = "New York")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()

    }else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: GetWeatherNetworkResponse, navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(title = "Im That Nigga")
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: GetWeatherNetworkResponse){
    Text(text = data.city.name)
}