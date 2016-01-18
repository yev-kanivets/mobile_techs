package net.hneu.ei.weatherapp.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test case class for module testing.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("ObjectEqualsNull")
public class WeatherEntryTest {
    private static final double DELTA = 0.0000000001;

    @Test
    public void testJsonParsing() {
        final String json = "{\"dt\":1452546000,\"main\":{\"temp\":269.2,\"temp_min\":266.348,"
                + "\"temp_max\":269.2,\"pressure\":1000.91,\"humidity\":92},\"weather\":[{\"main\":"
                + "\"Snow\",\"description\":\"light snow\"}],\"wind\":{\"speed\":5.31,"
                + "\"deg\":114.004},\"dt_txt\":\"2016-01-11 21:00:00\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherEntry weatherEntry = null;
        try {
            weatherEntry = objectMapper.readValue(json, WeatherEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weatherEntry);

        assertEquals(weatherEntry.getTimestamp(), 1452546000);

        assertEquals(weatherEntry.getMainEntry().getTemp(), 269.2, DELTA);
        assertEquals(weatherEntry.getMainEntry().getTempMin(), 266.348, DELTA);
        assertEquals(weatherEntry.getMainEntry().getTempMax(), 269.2, DELTA);
        assertEquals(weatherEntry.getMainEntry().getPressure(), 1000.91, DELTA);
        assertEquals(weatherEntry.getMainEntry().getHumidity(), 92, DELTA);

        assertEquals(weatherEntry.getWeather()[0].getMain(), "Snow");
        assertEquals(weatherEntry.getWeather()[0].getDescription(), "light snow");

        assertEquals(weatherEntry.getWind().getSpeed(), 5.31, DELTA);
        assertEquals(weatherEntry.getWind().getDegree(), 114.004, DELTA);

        assertEquals(weatherEntry.getTimestampTxt(), "2016-01-11 21:00:00");
    }

    @Test
    public void testFullJsonParsing() {
        final String json = "{\"dt\":1452783600,\"main\":{\"temp\":1.2,\"temp_min\":-1.35,"
                + "\"temp_max\":1.2,\"pressure\":1000.35,\"sea_level\":1023.7,"
                + "\"grnd_level\":1000.35,\"humidity\":92,\"temp_kf\":2.55},"
                + "\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\","
                + "\"icon\":\"13n\"}],\"clouds\":{\"all\":76},\"wind\":{\"speed\":3.4,"
                + "\"deg\":249.004},\"snow\":{\"3h\":0.072},\"sys\":{\"pod\":\"n\"},"
                + "\"dt_txt\":\"2016-01-14 15:00:00\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherEntry weatherEntry = null;
        try {
            weatherEntry = objectMapper.readValue(json, WeatherEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weatherEntry);

        assertEquals(weatherEntry.getTimestamp(), 1452783600);

        assertEquals(weatherEntry.getMainEntry().getTemp(), 1.2, DELTA);
        assertEquals(weatherEntry.getMainEntry().getTempMin(), -1.35, DELTA);
        assertEquals(weatherEntry.getMainEntry().getTempMax(), 1.2, DELTA);
        assertEquals(weatherEntry.getMainEntry().getPressure(), 1000.35, DELTA);
        assertEquals(weatherEntry.getMainEntry().getHumidity(), 92, DELTA);

        assertEquals(weatherEntry.getWeather()[0].getMain(), "Snow");
        assertEquals(weatherEntry.getWeather()[0].getDescription(), "light snow");

        assertEquals(weatherEntry.getWind().getSpeed(), 3.4, DELTA);
        assertEquals(weatherEntry.getWind().getDegree(), 249.004, DELTA);

        assertEquals(weatherEntry.getTimestampTxt(), "2016-01-14 15:00:00");
    }

    @Test
    public void testEquals() {
        final String json = "{\"dt\":1452546000,\"main\":{\"temp\":269.2,\"temp_min\":266.348,"
                + "\"temp_max\":269.2,\"pressure\":1000.91,\"humidity\":92},\"weather\":[{\"main\":"
                + "\"Snow\",\"description\":\"light snow\"}],\"wind\":{\"speed\":5.31,"
                + "\"deg\":114.004},\"dt_txt\":\"2016-01-11 21:00:00\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherEntry weatherEntry = null;
        try {
            weatherEntry = objectMapper.readValue(json, WeatherEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weatherEntry);

        assertFalse(weatherEntry.equals(null));

        assertFalse(weatherEntry.equals(new WeatherEntry()));

        assertTrue(weatherEntry.equals(weatherEntry.clone()));
    }
}