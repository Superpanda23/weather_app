package ph.alolodstudios.weatherapp.data.dto


import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("pressure_msl")
    val pressure: List<Double>,
    @SerializedName("relativehumidity_2m")
    val humidities: List<Double>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    val time: List<String>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>,
    @SerializedName("windspeed_10m")
    val windSpeed: List<Double>
)