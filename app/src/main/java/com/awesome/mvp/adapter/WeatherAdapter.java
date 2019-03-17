package com.awesome.mvp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.awesome.mvp.R;
import com.awesome.mvp.mode.weather.Forecast;

import java.util.List;

public class WeatherAdapter extends BaseQuickAdapter<Forecast,BaseViewHolder>{
    public WeatherAdapter(int layoutResId, @Nullable List<Forecast> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Forecast item) {

        helper.setText(R.id.tv_1,item.getFengli())
                .setText(R.id.tv_2,item.getHigh())
                .setText(R.id.tv_3,item.getType());
    }
}
