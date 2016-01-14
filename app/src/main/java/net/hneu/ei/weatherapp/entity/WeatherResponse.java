package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Entity class for response about weather.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse implements Parcelable, Cloneable {
    @JsonProperty("cod")
    private int responseCode;

    @JsonProperty("list")
    private WeatherEntry[] weatherEntries;

    public WeatherResponse() {
    }

    protected WeatherResponse(Parcel in) {
        responseCode = in.readInt();
        weatherEntries = in.createTypedArray(WeatherEntry.CREATOR);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public WeatherEntry[] getWeatherEntries() {
        return weatherEntries;
    }

    public void setWeatherEntries(WeatherEntry[] weatherEntries) {
        this.weatherEntries = weatherEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WeatherResponse) {
            WeatherResponse weatherResponse = (WeatherResponse) o;
            return weatherResponse.getResponseCode() == getResponseCode()
                    && Arrays.equals(weatherResponse.getWeatherEntries(), getWeatherEntries());
        } else return false;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(responseCode);
        dest.writeTypedArray(weatherEntries, flags);
    }
}