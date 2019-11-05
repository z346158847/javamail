package com.zwj.javamail.entity;

/**
 * 邮件实体
 */
public class EmailEntity {

    /**
     * 收件人   "xxx@qq.com;xxx@qq.com"
     */
    private String receiver;
    /**
     * 标题
     */
    private String subject;
    /**
     * 没用
     */
    private String text;
    /**
     * 内容
     */
    private String content;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}