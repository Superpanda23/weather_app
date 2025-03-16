package ph.alolodstudios.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ph.alolodstudios.weatherapp.data.location.DefaultLocationTracker
import ph.alolodstudios.weatherapp.data.remote.WeatherApi
import ph.alolodstudios.weatherapp.data.repository.WeatherRepositoryImpl
import ph.alolodstudios.weatherapp.domain.location.LocationTracker
import ph.alolodstudios.weatherapp.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}