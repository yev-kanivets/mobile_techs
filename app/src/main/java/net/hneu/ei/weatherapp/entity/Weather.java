package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.hneu.ei.weatherapp.util.StringUtils;

/**
 * Entity class for weather data, is included in {@link WeatherEntry}.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Parcelable, Cloneable {
    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    public Weather() {
    }

    protected Weather(Parcel in) {
        main = in.readString();
        description = in.readString();
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Weather) {
            Weather weather = (Weather) o;
            return StringUtils.equals(weather.getMain(), getMain())
                    && StringUtils.equals(weather.getDescription(), getDescription());
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

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(main);
        dest.writeString(description);
    }
}