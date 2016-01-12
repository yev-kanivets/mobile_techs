package net.hneu.ei.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.hneu.ei.weatherapp.R;

/**
 * Created by vobideyko on 1/12/16.
 */
public class RecyclerViewWeatherAdapter extends RecyclerView.Adapter<RecyclerViewWeatherAdapter.ViewHolder> {
    // Коллекция данных о погоде
    private String[] mDataset;

    // Предостаяляет доступ к элементам каждого элемента данных
    // Сложные элементы данных могут требовать более одного View в контейнере элемента для отображения
    // мы предоставляем доступ ко всем этим View для элемента данных во view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            //mTextView = v;
        }
    }

    // Конструктор принимает на вход коллекцию с данными для отображения
    public RecyclerViewWeatherAdapter(String[] myDataset) {
        //mDataset = myDataset;
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
        //holder.mTextView.setText(mDataset[position]);

    }

    // Возвращаем размер коллекции dataset (вызывает layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}