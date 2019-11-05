package com.zwj.javamail.vo;


import com.zwj.javamail.entity.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 用户信息包装类
 * @author wjzhang
 */
@Configuration
@ConfigurationProperties("customize")
public class UserInfoVo {

    private List<UserInfo> userinfolist;

    public List<UserInfo> getUserinfolist() {
        return userinfolist;
    }

    public void setUserinfolist(List<UserInfo> userinfolist) {
        this.userinfolist = userinfolist;
    }
}
