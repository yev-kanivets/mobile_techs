package net.hneu.ei.weatherapp;

import android.app.Application;
import android.content.Context;

import java.net.InetAddress;

/**
 * Created by vobideyko on 1/18/16.
 */
public class WeatherApp extends Application {

    /**
     * Проверяем есть ли выход в интернет
     * @return true если есть подключение к интернету, false - если нет.
     */
    public static boolean isInternetEnabled(){
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
