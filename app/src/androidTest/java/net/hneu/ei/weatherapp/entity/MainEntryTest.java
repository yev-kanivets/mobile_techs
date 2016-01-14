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
public class MainEntryTest extends TestCase {
    public void testParcelable() {
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

        Parcel parcel = Parcel.obtain();
        mainEntry.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        MainEntry mainEntryFromParcel = MainEntry.CREATOR.createFromParcel(parcel);
        assertEquals(mainEntry, mainEntryFromParcel);
    }
}