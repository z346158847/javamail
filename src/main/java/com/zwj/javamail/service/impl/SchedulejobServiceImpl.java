package com.zwj.javamail.service.impl;

import com.zwj.javamail.config.EmailConfig;
import com.zwj.javamail.entity.*;
import com.zwj.javamail.enums.ConstellationEnum;
import com.zwj.javamail.service.ImgDownLoadService;
import com.zwj.javamail.service.OkhttpGetDataService;
import com.zwj.javamail.service.SchedulejobService;
import com.zwj.javamail.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 *
 * @author wjzhang
 */
@Service("schedulejobservice")
public class SchedulejobServiceImpl implements SchedulejobService {


    /**
     * 计数器
     */
    private int index = 1;

    private static Logger log = LoggerFactory.getLogger(SchedulejobServiceImpl.class);
    @Autowired
    EmailConfig mc;
    @Autowired
    private SchedulejobService schedulejobService;
    @Autowired
    private ImgDownLoadService imgDownLoadService;
    @Autowired
    private OkhttpGetDataService okhttpGetDataService;
    @Autowired
    private UserInfoVo userInfoVo;




    /**
     * 定时任务  主要！
     */
    @Override
    @Scheduled(cron = "${scheduled.cron}")
    public void sendEmail() {
        // 获取图片url
        String imgUrl = okhttpGetDataService.getImgUrl();
        // 将图片保存到本地
        imgDownLoadService.saveImgNameCache(imgUrl, index);

        // 城市id  101010100 北京
        // 城市id  101090101 石家庄
        // 城市id  101030100 天津
        // 城市id  101100101 太原
        // 城市id  101100401 晋中
        // 城市id  101220101 合肥

        List<EmailEntity> emailEntityList = new ArrayList<>();
        userInfoVo.getUserinfolist().forEach(entity -> emailEntityList.add(getEmailEntity(entity)));

        emailEntityList.forEach(entity -> {
            mc.sendMimeMail(entity, index);
            log.info(entity.getReceiver() + "：第" + index + "次，successful to send message!");
        });


        // 日志
        log.info("今日发送完毕！");
        // 计数器
        index++;
    }



    @Override
    public EmailEntity getEmailEntity(UserInfo userInfo) {
        log.info("开始准备发送邮件的信息：{}", userInfo);
        // 每日一句
        WellSayJson wellSayJson = okhttpGetDataService.getWellSayJson();
        // 天气对象
        WeatherJsonData weatherJsonData = okhttpGetDataService.getWeatherInfo(userInfo.getCitycode());
        // 星座运势
        ConstellationJsonInfoVo constellationJsonInfoVo = okhttpGetDataService.getConstellationJson(userInfo.getConsname());
        // 新闻
        List<NewsJsonDataInfo> newsJsonDataInfoList = okhttpGetDataService.getNewsJsonDataInfo();
        // 节假日
        String holidayJson = okhttpGetDataService.getHolidayJson();

        // 标题
        String subject = "数据异常";


        String content = "数据异常";

        // 954649451
        if ("954649451@qq.com".equals(userInfo.getReceiver())) {
            subject = wellSayJson.getHitokoto() + "  今日天气:" + weatherJsonData.getWeather() + "。乖巧、可爱plus~";
            content = processDataOnly(wellSayJson, weatherJsonData, constellationJsonInfoVo, newsJsonDataInfoList, holidayJson);
        } else {
            subject = wellSayJson.getHitokoto() + "  今日天气:" + weatherJsonData.getWeather() + "。";
            content = processData(wellSayJson, weatherJsonData, constellationJsonInfoVo, holidayJson);
        }
        // 邮件内容拼接

        EmailEntity email = new EmailEntity();
        // 收件人
        email.setReceiver(userInfo.getReceiver());
        // 标题
        email.setSubject(subject);
        // 内容
        email.setContent(content);

        return email;
    }






    public String processData(WellSayJson wellSayJson, WeatherJsonData weatherJsonData, ConstellationJsonInfoVo constellationJsonInfoVo, String holidayJson) {
        String content = "";
        ConstellationJsonInfo constellationJsonInfo = constellationJsonInfoVo.getDay();
        String consName = ConstellationEnum.getNameByCode(constellationJsonInfoVo.getStar());


        String tips = "<li style=\'color:red\'>温馨提示：" +
                "<ul>";


        List<WeatherInfo> weatherInfoList = weatherJsonData.getIndex();

        for (int i = 0; i < weatherInfoList.size(); i++) {
            tips = tips + "<li>" + weatherInfoList.get(i).getIname() + "：" + weatherInfoList.get(i).getIvalue() + "。" + weatherInfoList.get(i).getDetail() + "</li>";
        }


        content = "<html><head>" +
                "<body>" +
                "<br>" +
                "<ul>" +
                "<p style=\'color:#9900CC\'>" +
                holidayJson +
                "</p>" +
                "</ul>" +
                "<ul>" +
                "<h3>每日一句</h3>" +
                "<p>" + wellSayJson.getHitokoto() + "</p>" +
                "<p>----作者：《" + wellSayJson.getCreator() + "》</p>" +
                "<p>----来自：《" + wellSayJson.getFrom() + "》</p>" +
                "</ul>" +
                "<ul>" +
                "<h3>天气情况</h3>" +
                "<li>地点：" + weatherJsonData.getCity() + "</li>" +
                "<li>天气：" + weatherJsonData.getWeather() + "</li>" +
                "<li>温度：" + weatherJsonData.getTemplow() + "~" + weatherJsonData.getTemphigh() + "度。当前气温：" + weatherJsonData.getTemp() + "度。</li>" +
                "<li>风力：" + weatherJsonData.getWinddirect() + weatherJsonData.getWindpower() + "</li>";

        content = content + tips +
                "</ul>" +
                "</li>" +
                "</ul>" +
                "<ul>" +
                "<h3>星座运势</h3>" +
                "<li>" + consName + "</li>" +
                "<li>幸运色：" + constellationJsonInfo.getLucky_color() + "</li>" +
                "<li>幸运数字：" + constellationJsonInfo.getLucky_num() + "</li>" +
                "<li>幸运时间：" + constellationJsonInfo.getLucky_time() + "</li>" +
                "<li style=\'color:red\'>速配星座：" + constellationJsonInfo.getGrxz() + "</li>" +
                "<li>爱情运势：" + constellationJsonInfo.getLove_txt() + "</li>" +
                "<li>工作运势：" + constellationJsonInfo.getWork_txt() + "</li>" +
                "<li>财富运势：" + constellationJsonInfo.getMoney_txt() + "</li>" +
                "<li >今日概述：" + constellationJsonInfo.getDay_notice() + "</li>" +
                "<li style=\'color:blue\'>运势简评：" + constellationJsonInfo.getGeneral_txt() + "</li></ul>" +
                "<ul><h3>每日一图</h3></ul><br>" +
                "<img src=\"cid:imageurl\"/>" +
                "</body></html>";


        return content;


    }


    public String processDataOnly(WellSayJson wellSayJson, WeatherJsonData weatherJsonData, ConstellationJsonInfoVo constellationJsonInfoVo, List<NewsJsonDataInfo> newsJsonDataInfoList, String holidayJson) {
        String content = "";
        ConstellationJsonInfo constellationJsonInfo = constellationJsonInfoVo.getDay();
        String consName = ConstellationEnum.getNameByCode(constellationJsonInfoVo.getStar());

        int newNumber = 20;

        String newInfo = "";
        for (int i = 0; i < newNumber; i++) {
            newInfo = newInfo + "<p><a href=\'" + newsJsonDataInfoList.get(i).getUrl() + "\' target=\'_blank\'>" + newsJsonDataInfoList.get(i).getTitle() + "</a></p>";
        }
        String tips = "<li style=\'color:red\'>温馨提示：" +
                "<ul>";


        List<WeatherInfo> weatherInfoList = weatherJsonData.getIndex();

        for (int i = 0; i < weatherInfoList.size(); i++) {
            tips = tips + "<li>" + weatherInfoList.get(i).getIname() + "：" + weatherInfoList.get(i).getIvalue() + "。" + weatherInfoList.get(i).getDetail() + "</li>";
        }


        content = "<html><head>" +
                "<body>" +
                "<br>" +
                "<p style=\'color:#9900CC\'>" +
                holidayJson +
                "</p>" +
                "<ul><h3>每日一句</h3>" +
                "<p>" + wellSayJson.getHitokoto() + "</p>" +
                "<p>----作者：《" + wellSayJson.getCreator() + "》</p>" +
                "<p>----来自：《" + wellSayJson.getFrom() + "》</p>" +
                "</ul>" +
                "<ul>" +
                "<h3>天气情况</h3>" +
                "<li>地点：" + weatherJsonData.getCity() + "</li>" +
                "<li>天气：" + weatherJsonData.getWeather() + "</li>" +
                "<li>温度：" + weatherJsonData.getTemplow() + "~" + weatherJsonData.getTemphigh() + "度。当前气温：" + weatherJsonData.getTemp() + "度。</li>" +
                "<li>风力：" + weatherJsonData.getWinddirect() + weatherJsonData.getWindpower() + "</li>";

        content = content + tips +
                "</ul>" +
                "</li>" +
                "</ul>" +
                "<ul>" +
                "<h3>星座运势</h3>" +
                "<li>" + consName + "</li>" +
                "<li>幸运色：" + constellationJsonInfo.getLucky_color() + "</li>" +
                "<li>幸运数字：" + constellationJsonInfo.getLucky_num() + "</li>" +
                "<li>幸运时间：" + constellationJsonInfo.getLucky_time() + "</li>" +
                "<li style=\'color:red\'>速配星座：" + constellationJsonInfo.getGrxz() + "</li>" +
                "<li>爱情运势：" + constellationJsonInfo.getLove_txt() + "</li>" +
                "<li>工作运势：" + constellationJsonInfo.getWork_txt() + "</li>" +
                "<li>财富运势：" + constellationJsonInfo.getMoney_txt() + "</li>" +
                "<li >今日概述：" + constellationJsonInfo.getDay_notice() + "</li>" +
                "<li style=\'color:blue\'>运势简评：" + constellationJsonInfo.getGeneral_txt() + "</li></ul>" +
                "<ul>" +
                newInfo +
                "</ul>" +
                "<ul><h3>每日一图</h3></ul><br>" +
                "<img src=\"cid:imageurl\"/>" +
                "</body></html>";


        return content;

    }


}
