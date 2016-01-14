package net.hneu.ei.weatherapp.model;

import net.hneu.ei.weatherapp.entity.WeatherResponse;

/**
 * Interface to give abstraction of data access.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public interface WeatherRepo {
    void fetchWeather(String query, WeatherCallback callback);

    interface WeatherCallback {
        void done(WeatherResponse weatherResponse);
    }
}