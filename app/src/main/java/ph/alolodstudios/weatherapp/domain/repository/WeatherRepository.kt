package ph.alolodstudios.weatherapp.domain.repository

import ph.alolodstudios.weatherapp.domain.util.Resource
import ph.alolodstudios.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>
}