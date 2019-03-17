/**
  * Copyright 2018 bejson.com 
  */
package com.awesome.mvp.mode.weather;
import java.util.List;

/**
 * Auto-generated: 2018-11-06 15:23:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Yesterday yesterday;
    private String city;
    private String aqi;
    private List<Forecast> forecast;
    private String ganmao;
    private String wendu;
    public void setYesterday(Yesterday yesterday) {
         this.yesterday = yesterday;
     }
     public Yesterday getYesterday() {
         return yesterday;
     }

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setAqi(String aqi) {
         this.aqi = aqi;
     }
     public String getAqi() {
         return aqi;
     }

    public void setForecast(List<Forecast> forecast) {
         this.forecast = forecast;
     }
     public List<Forecast> getForecast() {
         return forecast;
     }

    public void setGanmao(String ganmao) {
         this.ganmao = ganmao;
     }
     public String getGanmao() {
         return ganmao;
     }

    public void setWendu(String wendu) {
         this.wendu = wendu;
     }
     public String getWendu() {
         return wendu;
     }

}