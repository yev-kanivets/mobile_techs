package net.hneu.ei.weatherapp.network;

import net.hneu.ei.weatherapp.entity.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface to fetch weather data.
 * Created on 1/18/16.
 *
 * @author Evgenii Kanivets
 */
public interface WeatherService {
    @GET("data/2.5/forecast?mode=json&units=metric&APPID=b70089e1af8a0ebd6de44e16ff0da259")
    Call<WeatherResponse> getWeather(@Query("q") String query);
}