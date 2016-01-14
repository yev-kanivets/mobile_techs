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
public class WeatherTest extends TestCase {
    public void testParcelable() {
        final String json = "{\"speed\":5.31,\"deg\":114.004}";

        ObjectMapper objectMapper = new ObjectMapper();
        Wind wind = null;
        try {
            wind = objectMapper.readValue(json, Wind.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(wind);

        Parcel parcel = Parcel.obtain();
        wind.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Wind windFromParcel = Wind.CREATOR.createFromParcel(parcel);
        assertEquals(wind, windFromParcel);
    }
}