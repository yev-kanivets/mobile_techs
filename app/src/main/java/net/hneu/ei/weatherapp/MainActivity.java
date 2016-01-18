package net.hneu.ei.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.hneu.ei.weatherapp.activity.DetailsActivity;
import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.mock.MockWeatherRepo;
import net.hneu.ei.weatherapp.network.RetrofitWeatherRepo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherRepo weatherRepo = new RetrofitWeatherRepo();
        weatherRepo.fetchWeather("Kharkiv", new WeatherRepo.WeatherCallback() {
            @Override
            public void done(WeatherResponse weatherResponse) {
                if (weatherResponse.getResponseCode() == 200) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra(DetailsActivity.KEY_PLACE, "Kharkiv");
                    intent.putExtra(DetailsActivity.KEY_WEATHER_ENTRY,
                            weatherResponse.getWeatherEntries()[0]);
                    startActivity(intent);
                }
            }
        });
    }
}