package com.zwj.javamail.entity;

/**
 * 节日情况
 */
public class HolidayJson {

    /**
     *
     */
    private Integer code;

    private String tts;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    @Override
    public String toString() {
        return "HolidayJson{" +
                "code=" + code +
                ", tts='" + tts + '\'' +
                '}';
    }
}
