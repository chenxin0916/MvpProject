package com.awesome.mvp.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.lang.reflect.Type;

public class GsonUtils {

    private final Gson mGson;

    private GsonUtils() {
        mGson = new Gson();
    }

    public static GsonUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final GsonUtils INSTANCE = new GsonUtils();
    }

    public  Gson getGson(){
       return  mGson;
    }

    public <T> T parseJson(Reader reader, Type type){
        JsonReader jsonReader = new JsonReader(reader);
        return mGson.fromJson(jsonReader,type);
    }

}
