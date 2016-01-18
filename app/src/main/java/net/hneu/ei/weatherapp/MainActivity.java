package net.hneu.ei.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;

import net.hneu.ei.weatherapp.adapters.RecyclerViewWeatherAdapter;
import net.hneu.ei.weatherapp.decoration.DividerItemDecoration;
import net.hneu.ei.weatherapp.entity.WeatherResponse;
import net.hneu.ei.weatherapp.model.api.WeatherRepo;
import net.hneu.ei.weatherapp.model.mock.MockWeatherRepoProvider;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText mTvSearch;
    private RecyclerView mRecyclerViewWeather;
    private RecyclerViewWeatherAdapter mRecyclerViewWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Устанавливаем флаг для экрана, который указывает, что title bar нам не нужен
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем ссылки на объекты элементов экрана
        mTvSearch = (EditText) findViewById(R.id.edtSearch);
        mRecyclerViewWeather = (RecyclerView) findViewById(R.id.recyclerViewWeather);

        //Устанавливаем слушатель изменения текста для поля поиска
        mTvSearch.addTextChangedListener(this);

        //Устанавливаем флаг, который говорит о том, что наш RecyclerView не будет меняться в размере, что улучшает производительность
        mRecyclerViewWeather.setHasFixedSize(true);

        //Устанавливаем LinearLayoutManager для нашего RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewWeather.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewWeather.addItemDecoration(itemDecoration);

        //Получаем данные из БД и если есть выход в интернет асинхронно загружаем актуальные данные
        MockWeatherRepoProvider mockWeatherRepoProvider = new MockWeatherRepoProvider();
        mockWeatherRepoProvider.getRepo().fetchWeather("Kharkiv", new WeatherRepo.WeatherCallback() {
            @Override
            public void done(WeatherResponse weatherResponse) {
                mRecyclerViewWeatherAdapter = new RecyclerViewWeatherAdapter(weatherResponse.getWeatherEntries());
                mRecyclerViewWeather.setAdapter(mRecyclerViewWeatherAdapter);
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //При изменении текста в поле поиска осуществляем поиск
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
