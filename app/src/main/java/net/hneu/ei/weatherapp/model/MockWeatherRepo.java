package net.hneu.ei.weatherapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hneu.ei.weatherapp.entity.WeatherResponse;

import java.io.IOException;

/**
 * Mock implementation of {@link WeatherRepo}
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public class MockWeatherRepo implements WeatherRepo {
    @Override
    public void fetchWeather(String query, WeatherCallback callback) {
        final String json = "{\"city\":{\"id\":706483,\"name\":\"Kharkiv\",\"coord\":{\"lon\":36.25,"
                + "\"lat\":50},\"country\":\"UA\",\"population\":0,\"sys\":{\"population\":0}},"
                + "\"cod\":\"200\",\"message\":0.0104,\"cnt\":40,\"list\":[{\"dt\":1452783600,"
                + "\"main\":{\"temp\":1.2,\"temp_min\":-1.35,\"temp_max\":1.2,\"pressure\":1000.35,"
                + "\"sea_level\":1023.7,\"grnd_level\":1000.35,\"humidity\":92,\"temp_kf\":2.55},"
                + "\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon"
                + "\":\"13n\"}],\"clouds\":{\"all\":76},\"wind\":{\"speed\":3.4,\"deg\":249.004},"
                + "\"snow\":{\"3h\":0.072},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2016-01-14 15:00:00\"}"
                + ",{\"dt\":1452794400,\"main\":{\"temp\":0.64,\"temp_min\":-1.77,\"temp_max\":0.64,"
                + "\"pressure\":1002.29,\"sea_level\":1025.72,\"grnd_level\":1002.29,\"humidity\":95,"
                + "\"temp_kf\":2.41},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":"
                + "\"light snow\",\"icon\":\"13n\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":4.11,"
                + "\"deg\":300.005},\"snow\":{\"3h\":0.1635},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2016-01-14 18:00:00\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = objectMapper.readValue(json, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (callback != null) callback.done(weatherResponse);
    }
}