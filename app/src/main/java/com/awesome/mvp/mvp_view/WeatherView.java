package com.awesome.mvp.mvp_view;

import com.awesome.mvp.mode.weather.Forecast;
import com.awesome.mvp.mvp_base.IView;

import java.util.List;

public interface WeatherView extends IView{

    void setViewText(String text);
    void setAdapterData(List<Forecast> list);
}
