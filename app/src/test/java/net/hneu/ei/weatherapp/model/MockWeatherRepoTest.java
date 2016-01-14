package net.hneu.ei.weatherapp.model;

import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.mock.MockWeatherRepo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case class for module testing. Example of {@link WeatherRepo} usage.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public class MockWeatherRepoTest {

    @Test
    public void testFetchWeather() throws Exception {
        WeatherRepo weatherRepo = new MockWeatherRepo();
        weatherRepo.fetchWeather("query", new WeatherRepo.WeatherCallback() {
            @Override
            public void done(WeatherResponse weatherResponse) {
                assertNotNull(weatherResponse);

                assertEquals(weatherResponse.getResponseCode(), 200);

                assertNotNull(weatherResponse.getWeatherEntries());
            }
        });
    }
}