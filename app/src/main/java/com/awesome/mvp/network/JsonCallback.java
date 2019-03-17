package com.awesome.mvp.network;

import android.util.Log;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.awesome.mvp.exception.ServerResponseException;
import com.awesome.mvp.mode.ServerMode;
import com.awesome.mvp.utils.GsonUtils;


import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallback<T extends ServerMode> extends AbsCallback<T> {

    private static String TAG = JsonCallback.class.getSimpleName();

    private  Type mType;

    protected JsonCallback(Type type){
        this.mType = type;
    }

    private JsonCallback(){}

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        Log.i(TAG,"onStart");
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        Log.i(TAG ,"convertResponse" +response.code());
        switch (response.code()){
            case 200:
                ResponseBody body = response.body();
                if (body == null){
                    return null;
                }
                T data = null;
                data = GsonUtils.getInstance().parseJson(body.charStream(),mType);

                if (data == null){
                   throw new Exception("json解析失败");
                }

                if (data.getCode() != ErrorHandler.RESPONSE_OK){
                    throw new ServerResponseException("请求错误",data.getCode());
                }

                return data;
            default:
                //如果需要处理300，400的异常码在这里
               return null;
        }
    }
}
