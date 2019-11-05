package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 天气预报json data
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherJson {

    /**
     * 状态码   0
     */
    private Integer status;
    /**
     * 状态  "ok"
     */
    private String msg;

    /**
     * 数据 只有一个
     */
    private WeatherJsonData result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WeatherJsonData getResult() {
        return result;
    }

    public void setResult(WeatherJsonData result) {
        this.result = result;
    }
}
