/**
  * Copyright 2018 bejson.com 
  */
package com.awesome.mvp.mode.weather;

import com.awesome.mvp.mode.ServerMode;

/**
 * Auto-generated: 2018-11-06 15:23:37
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WeatherResponse extends ServerMode{

    private String msg;
    private Data data;

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

}