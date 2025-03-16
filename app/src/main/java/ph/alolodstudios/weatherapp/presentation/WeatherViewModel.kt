package ph.alolodstudios.weatherapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ph.alolodstudios.weatherapp.domain.location.LocationTracker
import ph.alolodstudios.weatherapp.domain.repository.WeatherRepository
import ph.alolodstudios.weatherapp.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject  constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading ->{
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}