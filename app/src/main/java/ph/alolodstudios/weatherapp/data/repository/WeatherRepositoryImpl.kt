package ph.alolodstudios.weatherapp.data.repository

import ph.alolodstudios.weatherapp.data.mappers.toWeatherInfo
import ph.alolodstudios.weatherapp.data.remote.WeatherApi
import ph.alolodstudios.weatherapp.domain.repository.WeatherRepository
import ph.alolodstudios.weatherapp.domain.util.Resource
import ph.alolodstudios.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(
        lat: Double,
        long: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long,
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}