package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 天气预报 当前天气
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherJsonData {

    /**
     * 当前城市   "北京"
     */
    private String city;

    /**
     * 天气情况   "晴"
     */
    private String weather;
    /**
     * 当前气温   29
     */
    private String temp;
    /**
     * 最高气温  31
     */
    private String temphigh;
    /**
     * 最低气温   19
     */
    private String templow;
    /**
     * 湿度   44
     */
    private String humidity;
    /**
     * 气压  1005
     */
    private String pressure;
    /**
     * 风速  4.4
     */
    private String windspeed;

    /**
     * 风向  东南风
     */
    private String winddirect;
    /**
     * 风力   2级
     */
    private String  windpower;

    /**
     * 温馨提示
     */
    private List<WeatherInfo> index;

    public WeatherJsonData() {
    }


    public WeatherJsonData(String city, String weather, String temp, String temphigh, String templow, String humidity, String pressure, String windspeed, String winddirect, String windpower, List<WeatherInfo> index) {
        this.city = city;
        this.weather = weather;
        this.temp = temp;
        this.temphigh = temphigh;
        this.templow = templow;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windspeed = windspeed;
        this.winddirect = winddirect;
        this.windpower = windpower;
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public List<WeatherInfo> getIndex() {
        return index;
    }

    public void setIndex(List<WeatherInfo> index) {
        this.index = index;
    }
}
