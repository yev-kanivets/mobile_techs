package net.hneu.ei.weatherapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by vobideyko on 1/18/16.
 */
public class WeatherApp extends Application {

    /**
     * Проверяем есть ли выход в интернет
     * callback вернет true если есть подключение к интернету, false - если нет.
     */
    public static void checkInternetCon(final InternetStateCallback internetStateCallback, final Context context){
        new AsyncTask<Void,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(cm.getActiveNetworkInfo() != null) {
                    try {
                        InetAddress ipAddr = InetAddress.getByName("google.com");
                        if (ipAddr.equals("")) {
                            return false;
                        } else {
                            return true;
                        }
                    } catch (Exception e) {
                        Log.e("checkInternetCon", " error: " + e.toString());
                        return false;
                    }
                } else {
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(internetStateCallback!=null) {
                    internetStateCallback.isInternetEnable(aBoolean);
                }
            }
        }.execute();
    }

    public interface InternetStateCallback{
        void isInternetEnable(boolean result);
    }
}
