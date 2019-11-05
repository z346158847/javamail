package com.zwj.javamail.service.impl;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwj.javamail.entity.*;
import com.zwj.javamail.service.OkhttpGetDataService;
import okhttp3.*;
import org.apache.catalina.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 利用okhttp获取数据信息
 *
 * @author wjzhang
 */
@Service("okhttpgetdataservice")
public class OkhttpGetDataServiceImpl implements OkhttpGetDataService {
    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(OkhttpGetDataServiceImpl.class);
    /**
     * 返回的json字符串
     */
    private String string = "";
    /**
     * jackson
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * okhttp发送get请求  获取json字符串
     *
     * @param url url地址
     * @return json字符串
     */
    public String callGetConfig(String url) {

        //  连接对象
        OkHttpClient client = new OkHttpClient();
        //  建立请求  阿里需要 Authorization请求头
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "APPCODE 2dd201c029fb425aa6d322d8dc640bfa")
                .get()  //默认为GET请求，可以不写
                .build();
        // Call对象
        final Call call = client.newCall(request);
        try {
            // 同步调用
            Response response = call.execute();
            // 获取响应值
            string = response.body().string();
            // 打印日志
            log.info(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    @Override
    public String getImgUrl() {
        //  二次元图图片的一个api接口
        String url = "https://51shenyun.cn/api.php?return=json";
        //  返回的地址
        String imgUrl = "https://ws1.sinaimg.cn/large/0072Vf1pgy1foxk79mt63j31hc0u0qlp.jpg";
        //   okhttp请求
        String jsonStr = callGetConfig(url);
        //  目标对象包装
        ApiGetImageJson apiGetImageJson = null;
        try {
            // json转为目标对象
            apiGetImageJson = objectMapper.readValue(jsonStr, ApiGetImageJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiGetImageJson != null && apiGetImageJson.getImgurl() != null){
            // 获取对象中的图片地址
            imgUrl = apiGetImageJson.getImgurl();
        }

        return imgUrl;
    }

    /**
     * 天气预报默认值 防止获取不到数据
     * @return 天气预报默认值
     */
    private WeatherJsonData defaultWeatherInfo(){

        List<WeatherInfo> index = new ArrayList<>();
        index.add(new WeatherInfo());

        return new WeatherJsonData("数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", index);

    }

    @Override
    public WeatherJsonData getWeatherInfo(String cityCode) {
        // 天气api接口
        String url = "http://jisutqybmf.market.alicloudapi.com/weather/query?citycode=" + cityCode;
        // okhttp请求
        String jsonStr = callGetConfig(url);
        // 目标对象
        WeatherJson weatherJson = null;
        try {
            // json转为目标对象
            weatherJson = objectMapper.readValue(jsonStr, WeatherJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 天气信息对象
        WeatherJsonData weatherJsonData =  defaultWeatherInfo();
        // 判断返回码是否为 0
        if (weatherJson != null && weatherJson.getStatus() == 0) {
            weatherJsonData = weatherJson.getResult();
        }
        return weatherJsonData;
    }

    /**
     * 默认星座运势 防止获取不到数据
     * @return 默认星座运势
     */
    private ConstellationJsonInfoVo defaultCons(){
        ConstellationJsonInfo constellationJsonInfo = new ConstellationJsonInfo("数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常", "数据异常");

        return new ConstellationJsonInfoVo(1, "数据异常",constellationJsonInfo);
    }
    @Override
    public ConstellationJsonInfoVo getConstellationJson(String consName) {
        // 星座运势api
        String url = "http://ali-star-lucky.showapi.com/star?star=" + consName;
        // okhttp请求
        String jsonStr = callGetConfig(url);
        // 星座运势对象
        ConstellationJson constellationJson = null;
        try {
            // json转为对象
            constellationJson = objectMapper.readValue(jsonStr, ConstellationJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConstellationJsonInfoVo constellationJsonInfoVo = defaultCons();

        if (constellationJson != null && constellationJson.getShowapiResCode() == 0) {
            constellationJsonInfoVo = constellationJson.getShowapiResBody();
        }
        return constellationJsonInfoVo;
    }

    /**
     * 默认每日一句
     * @return 默认每日一句
     */
    private WellSayJson defaultWellSay(){
        return new WellSayJson(0, "服务器崩溃了", "数据异常", "数据异常", "数据异常", "数据异常");
    }


    @Override
    public WellSayJson getWellSayJson() {
        // 每日一句api
        String url = "https://v1.hitokoto.cn/?encode=json";
        // okhttp请求
        String jsonStr = callGetConfig(url);
        // 星座运势对象
        WellSayJson wellSayJson = defaultWellSay();
        try {
            // json转为对象
            wellSayJson = objectMapper.readValue(jsonStr, WellSayJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wellSayJson;
    }

    /**
     * 默认新闻
     * @return 默认新闻
     */
    private List<NewsJsonDataInfo> defaultNewInfo(){
        List<NewsJsonDataInfo> newsJsonDataInfoList = new ArrayList<>();

        NewsJsonDataInfo newsJsonDataInfo = new NewsJsonDataInfo();

        newsJsonDataInfoList.add(newsJsonDataInfo);
        return newsJsonDataInfoList;
    }
    @Override
    public List<NewsJsonDataInfo> getNewsJsonDataInfo() {
        // 新闻头条
        String url = "http://v.juhe.cn/toutiao/index?type=guonei&key=76c9cc48deef18e040ee0b630e0ebb50";
        // okhttp请求
        String jsonStr = callGetConfig(url);
        //
        NewsJson newsJson = null;
        NewsJsonData newsJsonData = null;
        List<NewsJsonDataInfo> newsJsonDataInfoList = defaultNewInfo();
        try {
            // json转为对象
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            newsJson = objectMapper.readValue(jsonStr, NewsJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (newsJson != null && newsJson.getResult() !=null & !newsJson.getResult().getData().isEmpty()){
            newsJsonData = newsJson.getResult();
            newsJsonDataInfoList = newsJsonData.getData();
        }
        return newsJsonDataInfoList;
    }




    @Override
    public String getHolidayJson() {
        // 节假日信息
        String url = "http://timor.tech/api/holiday/tts";
        // okhttp请求
        String jsonStr = callGetConfig(url);
        //
        HolidayJson holidayJson = null;
        try {
            // json转为对象
            holidayJson = objectMapper.readValue(jsonStr, HolidayJson.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer code = 0;
        String holidayInfo = "服务器崩溃了";
        if (holidayJson != null && code.equals(holidayJson.getCode())){
            holidayInfo =  holidayJson.getTts();
        }
        return holidayInfo;
    }



    @Override
    public String getIp() {
        // 获取客户端ip
        String url = "http://pv.sohu.com/cityjson?ie=utf-8";
        // okhttp请求
        String jsonStr = callGetConfig(url);
        // "var returnCitySN = {"cip": "114.246.94.102", "cid": "110000", "cname": "北京市"};"
        jsonStr = jsonStr.substring(jsonStr.indexOf("{") , jsonStr.indexOf("}") + 1);
        System.out.println(jsonStr);
        //
        HostIpJson hostIpJson = null;
        try {
            // json转为对象
            hostIpJson = objectMapper.readValue(jsonStr, HostIpJson.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String hostIpInfo = "未获取到ip";
        if (hostIpJson != null ){
            hostIpInfo =  hostIpJson.getCip();
        }
        return hostIpInfo;
    }


}
