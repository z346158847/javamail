package com.zwj.javamail.service.impl;

import com.zwj.javamail.service.ImgDownLoadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载图片到本地
 * @author wjzhang
 */

@Service("imgdownloadservice")
public class ImgDownLoadServiceImpl implements ImgDownLoadService {

    /**
     * 下载路径  yml中配置
     */
    @Value("${mail.smtp.img}")
    private String imgPath;




    @Override
    public void saveImgNameCache(String imgUrl, int index) {
        // 路径
        String path = imgUrl;
        URL url = null;

        //从网络上下载一张图片
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //建立一个网络链接
        HttpURLConnection con = null;
        try {
            url = new URL(path);
            con = (HttpURLConnection) url.openConnection();
            inputStream = con.getInputStream();
            // append  为true时往文件后继续添加  false直接覆盖
            outputStream = new FileOutputStream(new File(imgPath + index+".jpg"),false);
            int n = -1;
            byte b [] = new byte[1024];
            // 读取io流
            while ((n = inputStream.read(b)) != -1) {
                // 写入
                outputStream.write(b, 0, n);
            }
            // 刷新
            outputStream.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }
}
