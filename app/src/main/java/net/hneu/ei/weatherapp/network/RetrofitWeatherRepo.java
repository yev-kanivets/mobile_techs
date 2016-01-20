package net.hneu.ei.weatherapp.network;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hneu.ei.weatherapp.database.DatabaseHelper;
import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.JacksonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Custom implementation of {@link WeatherRepo} to get Weather data from server.
 * Created on 1/18/16.
 *
 * @author Evgenii Kanivets
 */
public class RetrofitWeatherRepo implements WeatherRepo {
    final WeatherService weatherService;

    public RetrofitWeatherRepo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public void fetchWeather(final String query, final WeatherCallback callback, final Context context) {
        Call<WeatherResponse> weatherResponseCall = weatherService.getWeather(query);
        weatherResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Response<WeatherResponse> response) {
                callback.done(response.body());
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                ObjectMapper objectMapper = new ObjectMapper();
                String strWeatherResponse = null;
                try {
                    strWeatherResponse = objectMapper.writerWithType(WeatherResponse.class).writeValueAsString(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                databaseHelper.addWeatherResponse(query, strWeatherResponse);
                databaseHelper.close();
            }

            @Override
            public void onFailure(Throwable t) {
                WeatherResponse weatherResponse = new WeatherResponse();
                weatherResponse.setResponseCode(400);
                callback.done(weatherResponse);
            }
        });
    }
}