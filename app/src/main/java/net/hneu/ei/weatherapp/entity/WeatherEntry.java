package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.hneu.ei.weatherapp.util.StringUtils;

import java.util.Arrays;

/**
 * Entity class for weather data time period.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherEntry implements Parcelable, Cloneable {
    @JsonProperty("dt")
    private long timestamp;

    @JsonProperty("main")
    private MainEntry mainEntry;

    @JsonProperty("weather")
    private Weather[] weather;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("dt_txt")
    private String timestampTxt;

    public WeatherEntry() {
    }

    protected WeatherEntry(Parcel in) {
        timestamp = in.readLong();
        mainEntry = in.readParcelable(MainEntry.class.getClassLoader());
        weather = in.createTypedArray(Weather.CREATOR);
        wind = in.readParcelable(Wind.class.getClassLoader());
        timestampTxt = in.readString();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MainEntry getMainEntry() {
        return mainEntry;
    }

    public void setMainEntry(MainEntry mainEntry) {
        this.mainEntry = mainEntry;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getTimestampTxt() {
        return timestampTxt;
    }

    public void setTimestampTxt(String timestampTxt) {
        this.timestampTxt = timestampTxt;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WeatherEntry) {
            WeatherEntry weatherEntry = (WeatherEntry) o;
            return weatherEntry.getTimestamp() == getTimestamp()
                    && weatherEntry.getMainEntry().equals(getMainEntry())
                    && Arrays.equals(weatherEntry.getWeather(), getWeather())
                    && weatherEntry.getWind().equals(getWind())
                    && StringUtils.equals(weatherEntry.getTimestampTxt(), getTimestampTxt());
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

    public static final Creator<WeatherEntry> CREATOR = new Creator<WeatherEntry>() {
        @Override
        public WeatherEntry createFromParcel(Parcel in) {
            return new WeatherEntry(in);
        }

        @Override
        public WeatherEntry[] newArray(int size) {
            return new WeatherEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeParcelable(mainEntry, flags);
        dest.writeTypedArray(weather, flags);
        dest.writeParcelable(wind, flags);
        dest.writeString(timestampTxt);
    }
}