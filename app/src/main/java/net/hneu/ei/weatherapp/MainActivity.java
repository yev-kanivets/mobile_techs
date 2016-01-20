package net.hneu.ei.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.hneu.ei.weatherapp.adapters.RecyclerViewWeatherAdapter;
import net.hneu.ei.weatherapp.decoration.DividerItemDecoration;
import net.hneu.ei.weatherapp.entity.WeatherEntry;
import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.GeneralWeatherRepoProvider;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.api.WeatherRepoProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mTvSearch;
    private Button mBtnSearch;
    private RecyclerView mRecyclerViewWeather;
    private RecyclerViewWeatherAdapter mRecyclerViewWeatherAdapter;

    private WeatherEntry[] weatherEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Устанавливаем флаг для экрана, который указывает, что title bar нам не нужен
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем ссылки на объекты элементов экрана
        mTvSearch = (EditText) findViewById(R.id.edtSearch);
        mBtnSearch = (Button) findViewById(R.id.btnSearch);
        mBtnSearch.setOnClickListener(this);
        mRecyclerViewWeather = (RecyclerView) findViewById(R.id.recyclerViewWeather);

        //Устанавливаем флаг, который говорит о том, что наш RecyclerView не будет меняться в размере, что улучшает производительность
        mRecyclerViewWeather.setHasFixedSize(true);

        //Устанавливаем LinearLayoutManager для нашего RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewWeather.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewWeather.addItemDecoration(itemDecoration);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearch:
                String strSearch = mTvSearch.getText().toString();
                if(strSearch.length()>0){
                    strSearch = strSearch.toLowerCase();
                    doSearch(strSearch);
                } else {
                    Toast.makeText(this, R.string.toast_enter_name_of_the_city_for_search, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void doSearch(final String strSearch){
        //Получаем данные из БД и если есть выход в интернет асинхронно загружаем актуальные данные
        final WeatherRepoProvider weatherRepoProvider = new GeneralWeatherRepoProvider();
        WeatherApp.checkInternetCon(new WeatherApp.InternetStateCallback() {
            @Override
            public void isInternetEnable(boolean result) {
                weatherRepoProvider.getRepo(result).fetchWeather(strSearch, new WeatherRepo.WeatherCallback() {
                            @Override
                            public void done(WeatherResponse weatherResponse) {
                                if (weatherResponse != null) {
                                    weatherEntries = weatherResponse.getWeatherEntries();
                                    mRecyclerViewWeatherAdapter = new RecyclerViewWeatherAdapter(weatherEntries);
                                    mRecyclerViewWeather.setAdapter(mRecyclerViewWeatherAdapter);
                                } else {
                                    weatherEntries = new WeatherEntry[]{};
                                    mRecyclerViewWeatherAdapter = new RecyclerViewWeatherAdapter(weatherEntries);
                                    mRecyclerViewWeather.setAdapter(mRecyclerViewWeatherAdapter);
                                }
                            }
                        },
                        MainActivity.this);
            }
        }, MainActivity.this);
    }
}