package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 星座运势  详情
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstellationJsonInfoVo {

    /**
     * 状态码 0
     */
    @JsonProperty("ret_code")
    private Integer retCode;

    /**
     * 星座
     */
    private String star;
    /**
     * 星座详情
     */
    private ConstellationJsonInfo day;

    public ConstellationJsonInfoVo() {
    }

    public ConstellationJsonInfoVo(Integer retCode, String star, ConstellationJsonInfo day) {
        this.retCode = retCode;
        this.star = star;
        this.day = day;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public ConstellationJsonInfo getDay() {
        return day;
    }

    public void setDay(ConstellationJsonInfo day) {
        this.day = day;
    }
}
