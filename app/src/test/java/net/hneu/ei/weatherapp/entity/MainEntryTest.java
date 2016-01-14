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
public class MainEntryTest {
    private static final double DELTA = 0.0000000001;

    @Test
    public void testJsonParsing() {
        final String json = "{\"temp\":269.2,\"temp_min\":266.348,"
                + "\"temp_max\":269.2,\"pressure\":1000.91,\"humidity\":92}";

        ObjectMapper objectMapper = new ObjectMapper();
        MainEntry mainEntry = null;
        try {
            mainEntry = objectMapper.readValue(json, MainEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(mainEntry);

        assertEquals(mainEntry.getTemp(), 269.2, DELTA);
        assertEquals(mainEntry.getTempMin(), 266.348, DELTA);
        assertEquals(mainEntry.getTempMax(), 269.2, DELTA);
        assertEquals(mainEntry.getPressure(), 1000.91, DELTA);
        assertEquals(mainEntry.getHumidity(), 92, DELTA);
    }

    @Test
    public void testFullJsonParsing() {
        final String json = "{\"temp\":1.2,\"temp_min\":-1.35,"
                + "\"temp_max\":1.2,\"pressure\":1000.35,\"sea_level\":1023.7,"
                + "\"grnd_level\":1000.35,\"humidity\":92,\"temp_kf\":2.55}";

        ObjectMapper objectMapper = new ObjectMapper();
        MainEntry mainEntry = null;
        try {
            mainEntry = objectMapper.readValue(json, MainEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(mainEntry);

        assertEquals(mainEntry.getTemp(), 1.2, DELTA);
        assertEquals(mainEntry.getTempMin(), -1.35, DELTA);
        assertEquals(mainEntry.getTempMax(), 1.2, DELTA);
        assertEquals(mainEntry.getPressure(), 1000.35, DELTA);
        assertEquals(mainEntry.getHumidity(), 92, DELTA);
    }

    @Test
    public void testEquals() {
        final String json = "{\"temp\":269.2,\"temp_min\":266.348,"
                + "\"temp_max\":269.2,\"pressure\":1000.91,\"humidity\":92}";

        ObjectMapper objectMapper = new ObjectMapper();
        MainEntry mainEntry = null;
        try {
            mainEntry = objectMapper.readValue(json, MainEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(mainEntry);

        assertFalse(mainEntry.equals(null));

        assertFalse(mainEntry.equals(new MainEntry()));

        assertTrue(mainEntry.equals(mainEntry.clone()));
    }
}