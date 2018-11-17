package at.osim.weather.mvvm

import at.osim.weather.ListEvent
import at.osim.weather.model.ResourceMapper
import at.osim.weather.model.WeatherModel
import io.reactivex.Observable

class WeatherListViewModel(private val model: WeatherModel, private val resources: ResourceMapper) {

    fun weather(): Observable<ListEvent<WeatherViewData>> {
        return model.weatherForecast().map {
            val items = it.items.map { weather ->
                WeatherViewData(
                    resources.condition(weather.condition),
                    resources.conditionIcon(weather.condition),
                    weather.tempMin,
                    weather.tempMax,
                    resources.date(weather.date)
                )
            }

            ListEvent(it.type, it.pos, items)
        }
    }
}