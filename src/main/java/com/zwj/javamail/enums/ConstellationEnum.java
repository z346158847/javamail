package com.zwj.javamail.enums;

/**
 * 星座拼音处理
 */
public enum  ConstellationEnum {
    /**
     * 拼音  星座
     */
    BAIYANG("baiyang", "白羊座"),
    JINNIU("jinniu", "金牛座"),
    SHUANGZI("shuangzi", "双子座"),
    JUXIE("juxie", "巨蟹座"),
    SHIZI("shizi", "狮子座"),
    CHUNV("chunv", "处女座"),
    TIANCHENG("tiancheng", "天秤座"),
    TIANXIE("tianxie", "天蝎座"),
    SHESHOU("sheshou", "射手座"),
    MOJIE("mojie", "摩羯座"),
    SHUIPING("shuiping", "水瓶座"),
    SHUANGYU("shuangyu", "双鱼座"),;

    private String consCode;
    private String consName;


    ConstellationEnum(String consCode, String consName) {
        this.consCode = consCode;
        this.consName = consName;
    }

    /**
     * 通过code 找 星座名
     * @param consCode
     * @return
     */
    public static String getNameByCode(String consCode) {
        for (ConstellationEnum value : ConstellationEnum.values()) {
            if (value.getConsCode().equals(consCode)){
                return value.getConsName();
            }
        }
        return "未匹配到星座";
    }




    public String getConsCode() {
        return consCode;
    }

    public void setConsCode(String consCode) {
        this.consCode = consCode;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }
}
