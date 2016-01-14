package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;

import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

import java.io.IOException;

/**
 * Android test to obtain some specific classes.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("ObjectEqualsNull")
public class WeatherEntryTest extends TestCase {
    public void testParcelable() {
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

        Parcel parcel = Parcel.obtain();
        weatherEntry.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        WeatherEntry weatherEntryFromParcel = WeatherEntry.CREATOR.createFromParcel(parcel);
        assertEquals(weatherEntry, weatherEntryFromParcel);
    }
}