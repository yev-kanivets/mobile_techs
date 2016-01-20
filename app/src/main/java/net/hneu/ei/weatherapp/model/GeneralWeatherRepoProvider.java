package net.hneu.ei.weatherapp.model;

import net.hneu.ei.weatherapp.WeatherApp;
import net.hneu.ei.weatherapp.database.DbWeatherRepo;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.api.WeatherRepoProvider;
import net.hneu.ei.weatherapp.network.RetrofitWeatherRepo;

/**
 * Created by vobideyko on 1/20/16.
 */
public class GeneralWeatherRepoProvider implements WeatherRepoProvider {

    @Override
    public WeatherRepo getRepo(boolean isInternetEnable) {
        if(isInternetEnable){
            return new RetrofitWeatherRepo();
        } else {
            return new DbWeatherRepo();
        }
    }
}