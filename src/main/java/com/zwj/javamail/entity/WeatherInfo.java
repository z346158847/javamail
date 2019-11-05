package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 天气预报 温馨提示
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    /**
     * 名字  空调指数
     */
    private String iname;
    /**
     * 适合   部分时间开启
     */
    private String ivalue;
    /**
     * 建议  天气热，到中午的时候您将会感到有点热，因此建议在午后较热时开启制冷空调。
     */
    private String detail;

    public WeatherInfo() {
    }

    public WeatherInfo(String iname, String ivalue, String detail) {
        this.iname = iname;
        this.ivalue = ivalue;
        this.detail = detail;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
