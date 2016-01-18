package net.hneu.ei.weatherapp.model.api;

import android.content.Context;

import net.hneu.ei.weatherapp.entity.WeatherResponse;

/**
 * Interface to give abstraction of data access.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public interface WeatherRepo {
    void fetchWeather(String query, WeatherCallback callback, Context context);

    interface WeatherCallback {
        void done(WeatherResponse weatherResponse);
    }
}