package net.hneu.ei.weatherapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import net.hneu.ei.weatherapp.entity.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by vobideyko on 1/18/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weather.db";

    //TABLE_WEATHER
    private static final String TABLE_WEATHER = "weather_table";
    private static final String COL_INDEX = "_id";
    private static final String COL_CITY = "city_name";
    private static final String COL_RESPONSE = "response";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WEATHER + " ("
                + COL_INDEX +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CITY + " TEXT NOT NULL, "
                + COL_RESPONSE + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER);
        onCreate(db);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////Exercises
    public void addWeatherResponse(String strCityName, WeatherResponse weatherResponse) {
        String strWR = getWeatherResponse(strCityName);
        if(strWR==null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_CITY, strCityName);
            values.put(COL_RESPONSE, weatherResponse.toString());

            db.insert(TABLE_WEATHER, null, values);
            db.close();
        } else {
            updateWeatherResponseByCity(strCityName, weatherResponse);
        }
    }

    public void deleteAllWeatherResponses() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEATHER, null, null);
        db.close();
    }

    public String getWeatherResponse(String strCityName) {
        String strWeatherResponse = null;
        String selectQuery = "SELECT  * FROM " + TABLE_WEATHER
                + " WHERE " + COL_CITY + " = \"" + strCityName + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                strWeatherResponse = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return strWeatherResponse;
    }

    private void updateWeatherResponseByCity(String strCityName, WeatherResponse weatherResponse) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_WEATHER
                + " SET "
                + COL_RESPONSE + " = \"" + weatherResponse.toString()
                + "\" WHERE " + COL_CITY + " = \"" + strCityName + "\";";
        db.execSQL(updateQuery);

        db.close();
    }
}