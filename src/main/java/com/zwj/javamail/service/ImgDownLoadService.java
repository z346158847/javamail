package com.zwj.javamail.service;

/**
 * 下载图片到本地
 * @author wjzhang
 */
public interface ImgDownLoadService {
    /**
     * 下载图片到本地
     * @param imgUrl 图片url
     * @param index 次数，文件名（1.jpg）
     */
    void saveImgNameCache(String imgUrl,int index);
}
