package com.zwj.javamail.service;

import com.zwj.javamail.entity.EmailEntity;
import com.zwj.javamail.entity.UserInfo;

/**
 * 定时任务
 * @author  wjzhang
 */

public interface SchedulejobService {

    /**
     * 构建邮件内容
     *
     * @param userInfo 用户信息
     * @return  邮件实体
     */
    EmailEntity getEmailEntity(UserInfo userInfo);

    /**
     * 临时调用一次全体发送
     */
    void sendEmail();



}
