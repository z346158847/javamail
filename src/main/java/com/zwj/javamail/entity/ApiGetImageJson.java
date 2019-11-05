package com.zwj.javamail.entity;

/**
 * 获取图片json对象
 */
public class ApiGetImageJson {
    /**
     * 状态码  0
     */
    private String code;
    /**
     * 图片url
     */
    private String imgurl;
    /**
     * 宽度
     */
    private String width;
    /**
     * 高度
     */
    private String height;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
