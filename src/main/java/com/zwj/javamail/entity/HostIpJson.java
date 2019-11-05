package com.zwj.javamail.entity;

/**
 * 获取ip
 */
public class HostIpJson {

    /**
     * ip
     */
    private String cip;
    /**
     * 城市id
     */
    private String cid;
    /**
     * 城市名
     */
    private String cname;

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "HostIpJson{" +
                "cip='" + cip + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
