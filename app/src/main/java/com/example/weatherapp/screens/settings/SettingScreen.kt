package com.example.weatherapp.screens.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.model.Unit
import com.example.weatherapp.widgets.WeatherAppBar

@Composable
fun SettingScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    var unitToggledState by remember {
        mutableStateOf(false)
    }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value
    val defaultChoice = if (choiceFromDb.isNullOrEmpty()) measurementUnits[0]
    else choiceFromDb[0].unit
    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            icon = Icons.Default.ArrowBack,
            false,
            navController = navController
        ) {
            navController.popBackStack()
        }
    }) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                IconToggleButton(
                    checked = !unitToggledState,
                    onCheckedChange = {
                        unitToggledState = !it
                        choiceState = if (unitToggledState) {
                            "Imperial (F)"
                        } else {
                            "Metric (C)"
                        }
                    }, modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f))
                ) {
                    Text(text = if (unitToggledState) "Fahrenheit F" else "Celsius C")


                }
                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(Unit(unit = choiceState))
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFEFBE42)
                    )
                ) {
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(4.dp),
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }

            }
        }
    }
}