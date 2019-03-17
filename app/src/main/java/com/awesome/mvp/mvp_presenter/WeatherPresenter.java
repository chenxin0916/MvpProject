package com.awesome.mvp.mvp_presenter;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.awesome.mvp.exception.ServerResponseException;
import com.awesome.mvp.mode.weather.Data;
import com.awesome.mvp.mode.weather.WeatherResponse;
import com.awesome.mvp.mvp_base.BasePresenter;
import com.awesome.mvp.mvp_view.WeatherView;
import com.awesome.mvp.network.ErrorHandler;
import com.awesome.mvp.network.JsonCallback;
import com.awesome.mvp.network.Url;
import com.awesome.mvp.utils.LogUtils;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    public void getWeather() {

        OkGo.<WeatherResponse>get(Url.WEATHER_URL).params("city", "深圳")
                .execute(new JsonCallback<WeatherResponse>(new TypeToken<WeatherResponse>() {
                }.getType()) {
                    @Override
                    public void onSuccess(Response<WeatherResponse> response) {
                        LogUtils.iTag(getTAG(), response.body().getMsg());
                        Data data = response.body().getData();
                        getView().setViewText(data.getCity());
                        getView().setAdapterData(data.getForecast());

                    }

                    @Override
                    public void onError(Response<WeatherResponse> response) {
                        super.onError(response);

                        if (response.getException() instanceof ServerResponseException){
                            //服务器返回的错误码
                            int code = ((ServerResponseException) response.getException()).getCode();

                            if (code == ErrorHandler.RESPONSE_AUTHER_FAILEDC) {
                                //自己界面处理
                                return;
                            }
                            ErrorHandler.handError(code);
                            return;
                        }
                        //http或者其他的异常信息处理
                        ErrorHandler.handError(response.code());
                        LogUtils.eTag(getTAG(), response.getException().toString());
                    }
                });

    }
}
