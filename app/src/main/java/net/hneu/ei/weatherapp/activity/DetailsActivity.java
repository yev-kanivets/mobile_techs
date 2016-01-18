package net.hneu.ei.weatherapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import net.hneu.ei.weatherapp.R;
import net.hneu.ei.weatherapp.entity.MainEntry;
import net.hneu.ei.weatherapp.entity.WeatherEntry;
import net.hneu.ei.weatherapp.entity.Wind;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "DetailsActivity";

    public static final String KEY_PLACE = "key_place";
    public static final String KEY_WEATHER_ENTRY = "key_weather_entry";

    private String place;
    private WeatherEntry weatherEntry;

    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_main_weather)
    TextView tvMainWeather;
    @Bind(R.id.tv_description)
    TextView tvDescription;
    @Bind(R.id.tv_temperature)
    TextView tvTemperature;
    @Bind(R.id.tv_pressure)
    TextView tvPressure;
    @Bind(R.id.tv_humidity)
    TextView tvHumidity;
    @Bind(R.id.tv_wind)
    TextView tvWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar();

        if (initData()) initViews();
        else finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        ButterKnife.bind(DetailsActivity.this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.weather_in, place));

        tvTime.setText(weatherEntry.getTimestampTxt());

        if (weatherEntry.getWeather() != null && weatherEntry.getWeather().length > 0
                && weatherEntry.getWeather()[0] != null) {
            tvMainWeather.setText(weatherEntry.getWeather()[0].getMain());
            tvDescription.setText(weatherEntry.getWeather()[0].getDescription());
        }

        if (weatherEntry.getMainEntry() != null) {
            MainEntry mainEntry = weatherEntry.getMainEntry();
            tvTemperature.setText(getString(R.string.temperature, mainEntry.getTemp(),
                    mainEntry.getTempMin(), mainEntry.getTempMax()));
            tvPressure.setText(getString(R.string.pressure, mainEntry.getPressure()));
            tvHumidity.setText(getString(R.string.humidity, mainEntry.getHumidity()));
        }

        if (weatherEntry.getWind() != null) {
            Wind wind = weatherEntry.getWind();
            tvWind.setText(getString(R.string.wind, wind.getSpeed(), wind.getDegree()));
        }
    }

    private boolean initData() {
        place = getIntent().getStringExtra(KEY_PLACE);
        weatherEntry = getIntent().getParcelableExtra(KEY_WEATHER_ENTRY);

        return place != null && weatherEntry != null;
    }
}