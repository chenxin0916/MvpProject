package com.awesome.mvp.screen.activity;
import android.support.v7.widget.LinearLayoutManager;

import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.awesome.mvp.App;
import com.awesome.mvp.R;
import com.awesome.mvp.adapter.WeatherAdapter;
import com.awesome.mvp.mode.weather.Forecast;
import com.awesome.mvp.mvp_base.BaseActivity;
import com.awesome.mvp.mvp_presenter.WeatherPresenter;
import com.awesome.mvp.mvp_view.WeatherView;
import com.awesome.mvp.view.RefreshRecycleView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends BaseActivity<WeatherPresenter> implements WeatherView{


    private TextView mCityName;
    private ArrayList<Forecast> mListData;
    private WeatherAdapter mWeatherAdapter;

    @Override
    protected int getView() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initStateBar(ImmersionBar immersionBar) {

    }

    @Override
    protected WeatherPresenter initPresent() {
        return new WeatherPresenter();
    }

    @Override
    protected void initData() {
        RefreshRecycleView refreshRecycleView = findViewById(R.id.refresh_recy);
        mCityName = findViewById(R.id.city);
        refreshRecycleView.setLineManager(true);
        mListData = new ArrayList<>();
        mWeatherAdapter = new WeatherAdapter(R.layout.forest_item, mListData);
        refreshRecycleView.setRcyAdapter(mWeatherAdapter);
        refreshRecycleView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

            }
        });

        getmPresenter().getWeather();
    }

    @Override
    public void setViewText(String text) {
        mCityName.setText(text);
    }

    @Override
    public void setAdapterData(List<Forecast> list) {
        mListData.addAll(list);
        mWeatherAdapter.notifyDataSetChanged();
    }

}
