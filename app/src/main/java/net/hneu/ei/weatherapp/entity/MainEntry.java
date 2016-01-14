package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for main weather data, is included in {@link WeatherEntry}.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainEntry implements Parcelable, Cloneable {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    @JsonProperty("pressure")
    private double pressure;

    @JsonProperty("humidity")
    private double humidity;

    public MainEntry() {
    }

    protected MainEntry(Parcel in) {
        temp = in.readDouble();
        tempMin = in.readDouble();
        tempMax = in.readDouble();
        pressure = in.readDouble();
        humidity = in.readDouble();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MainEntry) {
            MainEntry mainEntry = (MainEntry) o;
            return mainEntry.getTemp() == getTemp()
                    && mainEntry.getTempMin() == getTempMin()
                    && mainEntry.getTempMax() == getTempMax()
                    && mainEntry.getPressure() == getPressure()
                    && mainEntry.getHumidity() == getHumidity();
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

    public static final Creator<MainEntry> CREATOR = new Creator<MainEntry>() {
        @Override
        public MainEntry createFromParcel(Parcel in) {
            return new MainEntry(in);
        }

        @Override
        public MainEntry[] newArray(int size) {
            return new MainEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(tempMin);
        dest.writeDouble(tempMax);
        dest.writeDouble(pressure);
        dest.writeDouble(humidity);
    }
}