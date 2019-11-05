package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 名人名言json
 */
public class WellSayJson {
    /**
     * id
     */
    private Integer id;
    /**
     * 名言内容
     */
    private String hitokoto;
    /**
     * 类型
     */
    private String type;
    /**
     * 出自
     */
    private String from;

    private String creator;
    @JsonProperty("created_at")
    private String createdAt;

    public WellSayJson() {
    }

    public WellSayJson(Integer id, String hitokoto, String type, String from, String creator, String createdAt) {
        this.id = id;
        this.hitokoto = hitokoto;
        this.type = type;
        this.from = from;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
