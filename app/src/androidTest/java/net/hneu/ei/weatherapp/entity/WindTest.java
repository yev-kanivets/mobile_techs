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
public class WindTest extends TestCase {
    public void testParcelable() {
        final String json = "{\"main\":\"Snow\",\"description\":\"light snow\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(json, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(weather);

        Parcel parcel = Parcel.obtain();
        weather.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Weather weatherFromParcel = Weather.CREATOR.createFromParcel(parcel);
        assertEquals(weather, weatherFromParcel);
    }
}