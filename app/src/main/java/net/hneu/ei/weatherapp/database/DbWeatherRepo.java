package net.hneu.ei.weatherapp.database;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;

import java.io.IOException;


/**
 * Created by vobideyko on 1/20/16.
 */
public class DbWeatherRepo implements WeatherRepo {

    private DatabaseHelper mDatabaseHelper;

    public DbWeatherRepo() {    }

    @Override
    public void fetchWeather(String query, final WeatherCallback callback, Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
        String json = mDatabaseHelper.getWeatherResponse(query);

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;

        if(json!=null) {
            try {
                weatherResponse = objectMapper.readValue(json, WeatherResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (callback != null) callback.done(weatherResponse);
    }
}
