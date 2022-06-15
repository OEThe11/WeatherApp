package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.Constants.BASE_URL
import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao
    = weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weatherD")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

}