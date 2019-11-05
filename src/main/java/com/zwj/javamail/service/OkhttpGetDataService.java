package com.zwj.javamail.service;


import com.zwj.javamail.entity.*;

import java.util.List;

/**
 * 利用okhttp获取数据信息
 * @author wjzhang
 */
public interface OkhttpGetDataService {


    /**
     * 获取二次元图片地址
     * @return  图片url
     */
    String getImgUrl();

    /**
     * 获取天气信息
     * @param city 城市名
     * @return  该城市天气信息
     */
    WeatherJsonData getWeatherInfo(String  city);

    /**
     * 获取星座运势
     * @param consName 星座名  摩羯座
     * @return 星座运势
     */
    ConstellationJsonInfoVo getConstellationJson(String consName);



    /**
     * 获取名人名言
     * @return 星座运势
     */
    WellSayJson getWellSayJson();


    /**
     * 新闻头条
     * @return 新闻头条
     */
    List<NewsJsonDataInfo> getNewsJsonDataInfo();

    /**
     * 节假日信息
     * @return 节假日信息
     */
    String getHolidayJson();


    /**
     * Ip
     * @return ip
     */
    String getIp();
}
