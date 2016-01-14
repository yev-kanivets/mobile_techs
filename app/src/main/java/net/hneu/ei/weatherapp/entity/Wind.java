package net.hneu.ei.weatherapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for weather wind data, is included in {@link WeatherEntry}.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind implements Parcelable, Cloneable {
    @JsonProperty("speed")
    private double speed;

    @JsonProperty("deg")
    private double degree;

    public Wind() {
    }

    protected Wind(Parcel in) {
        speed = in.readDouble();
        degree = in.readDouble();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Wind) {
            Wind wind = (Wind) o;
            return wind.getSpeed() == getSpeed()
                    && wind.getDegree() == getDegree();
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

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(speed);
        dest.writeDouble(degree);
    }
}