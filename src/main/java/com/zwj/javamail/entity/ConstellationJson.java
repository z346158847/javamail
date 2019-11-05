package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 星座运势
 * @author wjzhang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstellationJson {

    /**
     * 状态码  0
     */
    @JsonProperty("showapi_res_code")
    /**
     * 星座运势 详情
     */
    private Integer showapiResCode;
    @JsonProperty("showapi_res_body")
    private ConstellationJsonInfoVo showapiResBody;

    public Integer getShowapiResCode() {
        return showapiResCode;
    }

    public void setShowapiResCode(Integer showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    public ConstellationJsonInfoVo getShowapiResBody() {
        return showapiResBody;
    }

    public void setShowapiResBody(ConstellationJsonInfoVo showapiResBody) {
        this.showapiResBody = showapiResBody;
    }
}
