package com.zwj.javamail.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 星座运势  详情
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstellationJsonInfo {

    /**
     * 星座名  摩羯座
     */
    private String star;
    /**
     * 爱情运势  单身的会有热心人士牵线，为你恋情加速
     */
    private String love_txt;
    /**
     * 工作运势  工作或学习的进步不大，正是“磨刀”阶段。
     */
    private String work_txt;

    /**
     * 幸运色  红色
     */
    private String lucky_color;

    /**
     * 吉时   0:00-2:00
     */
    private String lucky_time;

    /**
     * 财富运势
     */
    private String money_txt;
    /**
     * 运势简评
     */
    private String general_txt;
    /**
     * 速配星座 天蝎座
     */
    private String grxz;

    /**
     *  幸运数字
     */
    private String lucky_num;

    /**
     * 今日提醒
     */
    private String day_notice;

    public ConstellationJsonInfo() {
    }

    public ConstellationJsonInfo(String star, String love_txt, String work_txt, String lucky_color, String lucky_time, String money_txt, String general_txt, String grxz, String lucky_num, String day_notice) {
        this.star = star;
        this.love_txt = love_txt;
        this.work_txt = work_txt;
        this.lucky_color = lucky_color;
        this.lucky_time = lucky_time;
        this.money_txt = money_txt;
        this.general_txt = general_txt;
        this.grxz = grxz;
        this.lucky_num = lucky_num;
        this.day_notice = day_notice;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLove_txt() {
        return love_txt;
    }

    public void setLove_txt(String love_txt) {
        this.love_txt = love_txt;
    }

    public String getWork_txt() {
        return work_txt;
    }

    public void setWork_txt(String work_txt) {
        this.work_txt = work_txt;
    }

    public String getLucky_color() {
        return lucky_color;
    }

    public void setLucky_color(String lucky_color) {
        this.lucky_color = lucky_color;
    }

    public String getLucky_time() {
        return lucky_time;
    }

    public void setLucky_time(String lucky_time) {
        this.lucky_time = lucky_time;
    }

    public String getMoney_txt() {
        return money_txt;
    }

    public void setMoney_txt(String money_txt) {
        this.money_txt = money_txt;
    }

    public String getGrxz() {
        return grxz;
    }

    public void setGrxz(String grxz) {
        this.grxz = grxz;
    }

    public String getLucky_num() {
        return lucky_num;
    }

    public void setLucky_num(String lucky_num) {
        this.lucky_num = lucky_num;
    }

    public String getDay_notice() {
        return day_notice;
    }

    public void setDay_notice(String day_notice) {
        this.day_notice = day_notice;
    }

    public String getGeneral_txt() {
        return general_txt;
    }

    public void setGeneral_txt(String general_txt) {
        this.general_txt = general_txt;
    }
}
