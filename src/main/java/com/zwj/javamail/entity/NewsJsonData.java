package com.zwj.javamail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsJsonData {
    private String stat;
    private List<NewsJsonDataInfo> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsJsonDataInfo> getData() {

        return data;
    }

    public void setData(List<NewsJsonDataInfo> data) {
        this.data = data;
    }
}
