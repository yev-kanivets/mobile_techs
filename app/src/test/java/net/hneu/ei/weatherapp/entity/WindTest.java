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
public class WindTest {
    private static final double DELTA = 0.0000000001;

    @Test
    public void testJsonParsing() {
        final String json = "{\"speed\":5.31,\"deg\":114.004}";

        ObjectMapper objectMapper = new ObjectMapper();
        Wind wind = null;
        try {
            wind = objectMapper.readValue(json, Wind.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(wind);

        assertEquals(wind.getSpeed(), 5.31, DELTA);
        assertEquals(wind.getDegree(), 114.004, DELTA);
    }

    @Test
    public void testEquals() {
        final String json = "{\"speed\":5.31,\"deg\":114.004}";

        ObjectMapper objectMapper = new ObjectMapper();
        Wind wind = null;
        try {
            wind = objectMapper.readValue(json, Wind.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(wind);

        assertFalse(wind.equals(null));

        assertFalse(wind.equals(new Wind()));

        assertTrue(wind.equals(wind.clone()));
    }
}