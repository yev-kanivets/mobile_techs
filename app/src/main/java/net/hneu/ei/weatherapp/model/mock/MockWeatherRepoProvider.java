package net.hneu.ei.weatherapp.model.mock;

import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.api.WeatherRepoProvider;

/**
 * Mock implementation of {@link WeatherRepoProvider}.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public class MockWeatherRepoProvider implements WeatherRepoProvider {

    @Override
    public WeatherRepo getRepo(boolean s) {
        return new MockWeatherRepo();
    }
}