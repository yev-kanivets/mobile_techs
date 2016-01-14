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
public class WeatherTest {
    @Test
    public void testJsonParsing() {
        final String json = "{\"main\":\"Snow\",\"description\":\"light snow\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(json, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weather);

        assertEquals(weather.getMain(), "Snow");
        assertEquals(weather.getDescription(), "light snow");
    }

    @Test
    public void testEquals() {
        final String json = "{\"main\":\"Snow\",\"description\":\"light snow\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(json, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weather);

        assertFalse(weather.equals(null));

        assertFalse(weather.equals(new Weather()));

        assertTrue(weather.equals(weather.clone()));
    }
}