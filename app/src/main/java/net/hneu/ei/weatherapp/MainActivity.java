package net.hneu.ei.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.TextView;

import net.hneu.ei.weatherapp.adapters.RecyclerViewWeatherAdapter;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private TextView mTvSearch;
    private RecyclerView mRecyclerViewWeather;
    private RecyclerViewWeatherAdapter mRecyclerViewWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Устанавливаем флаг для экрана, который указывает, что title bar нам не нужен
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем ссылки на объекты элементов экрана
        mTvSearch = (TextView) findViewById(R.id.tvSearch);
        mRecyclerViewWeather = (RecyclerView) findViewById(R.id.recyclerViewWeather);

        //Устанавливаем слушатель изменения текста для поля поиска
        mTvSearch.addTextChangedListener(this);

        //Устанавливаем флаг, который говорит о том, что наш RecyclerView не будет меняться в размере, что улучшает производительность
        mRecyclerViewWeather.setHasFixedSize(true);

        //Устанавливаем LinearLayoutManager для нашего RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewWeather.setLayoutManager(layoutManager);

        //Получаем данные из БД и если есть выход в интернет асинхронно загружаем актуальные данные
        //mAdapter = new MyAdapter(myDataset);
        //mRecyclerViewWeather.setAdapter(mAdapter);
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
