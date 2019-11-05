package com.zwj.javamail.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 用户信息
 * @author wjzhang
 */
@Configuration
public class UserInfo {

    /**
     * 用户名
     */
    private String name;
    /**
     * 邮箱
     */
    private String receiver;
    /**
     * 城市 代码
     */
    private String citycode;
    /**
     * 星座
     */
    private String consname;

    public UserInfo() {
    }

    public UserInfo(String name, String receiver, String citycode, String consname) {
        this.name = name;
        this.receiver = receiver;
        this.citycode = citycode;
        this.consname = consname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getConsname() {
        return consname;
    }

    public void setConsname(String consname) {
        this.consname = consname;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", receiver='" + receiver + '\'' +
                ", citycode='" + citycode + '\'' +
                ", consname='" + consname + '\'' +
                '}';
    }
}
