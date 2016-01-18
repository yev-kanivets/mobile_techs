package net.hneu.ei.weatherapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hneu.ei.weatherapp.MainActivity;
import net.hneu.ei.weatherapp.R;
import net.hneu.ei.weatherapp.entity.WeatherEntry;

/**
 * Created by vobideyko on 1/12/16.
 */
public class RecyclerViewWeatherAdapter extends RecyclerView.Adapter<RecyclerViewWeatherAdapter.ViewHolder> implements View.OnClickListener {
    // Коллекция данных о погоде
    private WeatherEntry[] mDataset;

    // Предостаяляет доступ к элементам каждого элемента данных
    // Сложные элементы данных могут требовать более одного View в контейнере элемента для отображения
    // мы предоставляем доступ ко всем этим View для элемента данных во view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvMain;
        public TextView mTvTemp;
        public TextView mTvTempMin;
        public TextView mTvTempMax;
        public TextView mTvDate;
        public TextView mTvDesc;
        public ViewHolder(View v) {
            super(v);
            mTvMain = (TextView) v.findViewById(R.id.tvMain);
            mTvTemp = (TextView) v.findViewById(R.id.tvTemp);
            mTvTempMin = (TextView) v.findViewById(R.id.tvTempMin);
            mTvTempMax = (TextView) v.findViewById(R.id.tvTempMax);
            mTvDate = (TextView) v.findViewById(R.id.tvDate);
            mTvDesc = (TextView) v.findViewById(R.id.tvDescription);
        }
    }

    // Конструктор принимает на вход коллекцию с данными для отображения
    public RecyclerViewWeatherAdapter(WeatherEntry[] myDataset) {
        mDataset = myDataset;
    }

    // Создаем новые View для отображения элементов коллекции (Вызывается лаяут менеджером)
    @Override
    public RecyclerViewWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // Создаем новый view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_weather, parent, false);

        // устанавливаем настройки view которые общие для всех элементов данных, например размер шрифтов цвет, отступы и тд.

        // создаем view holder, который предоставит удобный доступ к элементам позиции списка.
        ViewHolder vh = new ViewHolder( v);
        return vh;
    }

    // Заменяем наполнение view (вызывает layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(mDataset[position].getWeather().length>0) {
            holder.mTvMain.setText(mDataset[position].getWeather()[0].getMain());
            holder.mTvDesc.setText(mDataset[position].getWeather()[0].getDescription());
        }

        holder.mTvTemp.setText("Температура: " + mDataset[position].getMainEntry().getTemp());
        if(mDataset[position].getMainEntry().getTemp()<0){
            holder.mTvTemp.setTextColor(Color.BLUE);
        } else {
            holder.mTvTemp.setTextColor(Color.RED);
        }

        holder.mTvTempMin.setText("Минимальная температура: " + mDataset[position].getMainEntry().getTempMin());
        if(mDataset[position].getMainEntry().getTempMin()<0){
            holder.mTvTempMin.setTextColor(Color.BLUE);
        } else {
            holder.mTvTempMin.setTextColor(Color.RED);
        }

        holder.mTvTempMax.setText("Максимальная температура: " + mDataset[position].getMainEntry().getTempMax());
        if(mDataset[position].getMainEntry().getTempMax()<0){
            holder.mTvTempMax.setTextColor(Color.BLUE);
        } else {
            holder.mTvTempMax.setTextColor(Color.RED);
        }

        holder.mTvDate.setText(mDataset[position].getTimestampTxt());

        ((View)holder.mTvDate.getParent()).setTag(position);
        ((View)holder.mTvDate.getParent()).setOnClickListener(this);
    }

    // Возвращаем размер коллекции dataset (вызывает layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


    @Override
    public void onClick(View v) {
        startDetailsActivity(mDataset[((int) v.getTag())], v.getContext());
    }

    private void startDetailsActivity(WeatherEntry weatherEntry, Context context){
        // TODO: 1/18/16 start details activity
        /*Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("data", weatherEntry);
        context.startActivity(intent);*/
    }
}