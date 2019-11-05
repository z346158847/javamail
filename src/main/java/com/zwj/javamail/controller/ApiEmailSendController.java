package com.zwj.javamail.controller;

import com.zwj.javamail.config.EmailConfig;
import com.zwj.javamail.entity.EmailEntity;
import com.zwj.javamail.entity.HolidayJson;
import com.zwj.javamail.entity.HostIpJson;
import com.zwj.javamail.entity.UserInfo;
import com.zwj.javamail.service.ImgDownLoadService;
import com.zwj.javamail.service.OkhttpGetDataService;
import com.zwj.javamail.service.SchedulejobService;
import com.zwj.javamail.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 邮件api发送controller
 * @author wjzhang
 */

@Controller
@RequestMapping("api")
public class ApiEmailSendController {

    private static Logger log = LoggerFactory.getLogger(ApiEmailSendController.class);
    @Autowired
    private EmailConfig mc;
    @Autowired
    private ImgDownLoadService imgDownLoadService;
    @Autowired
    private OkhttpGetDataService okhttpGetDataService;
    @Autowired
    private SchedulejobService schedulejobService;

    private int index = 1;


    /**
     * 测试用接口
     */
    @ResponseBody
    @RequestMapping ("/emailtest")
    public String testSendEmail() {

        // 获取图片的url
        String imgUrl = okhttpGetDataService.getImgUrl();
        // 将图片下载到本地
        imgDownLoadService.saveImgNameCache(imgUrl, index);

        // 城市id  101010100 北京
        // 城市id  101090101 石家庄
        // 城市id  101030100 天津
        // 城市id  101100101 太原
        // 城市id  101100401 晋中
        // 获取邮件对象
        EmailEntity email = schedulejobService.getEmailEntity(new UserInfo("测试", "346158847@qq.com", "101010100", "mojie"));
        // 发送邮件
        mc.sendMimeMail(email, index);
        // 记录第几次发送
        log.info("successful to send message!" + "第"+ index+ "次发送");
        // 计数
        index++;


        return "success";
    }


    @ResponseBody
    @GetMapping("/email")
    public synchronized String sendEmailConfig(@RequestParam Map<String, String> map){
        log.info("城市：" + map.get("city") + "。邮箱：" + map.get("receiver") + "。星座：" + map.get("cons"));

        UserInfo userInfo = new UserInfo("自定义", map.get("receiver"), map.get("city"), map.get("cons") );


        // 获取图片的url
        String imgUrl = okhttpGetDataService.getImgUrl();
        // 将图片下载到本地
        imgDownLoadService.saveImgNameCache(imgUrl, index);

        // 获取邮件对象
        EmailEntity email = schedulejobService.getEmailEntity(userInfo);
        // 发送邮件
        mc.sendMimeMail(email, index);
        // 记录第几次发送
        log.info("successful to send message!" + "第"+ index+ "次发送");
        // 计数
        index++;

        return map.get("receiver") + "的邮件已发送成功。";

    }

    @ResponseBody
    @RequestMapping("/temporary")
    public synchronized String temporary(@RequestParam Map<String, String>  map){
        schedulejobService.sendEmail();
//        userInfoVo.getUserinfolist().forEach(entity -> System.out.println(entity.getName()));

        return "临时发送。";

    }


    @ResponseBody
    @RequestMapping("/test")
    public void getIp(@RequestBody HostIpJson params){

        System.out.println(params.toString());
        log.info("获取到该ip -> {}" , params );

    }

    @RequestMapping("/test1")
    public void test(){

        System.out.println("111");

    }

    @RequestMapping("/")
    public  ModelAndView  getIp1(){

        return new ModelAndView("/");

    }

    class HostIpinfo{
        private String cid;
        private String cip;
        private String cname;

        public HostIpinfo() {
        }

        public String getCip() {
            return cip;
        }

        public void setCip(String cip) {
            this.cip = cip;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        @Override
        public String toString() {
            return "HostIpinfo{" +
                    "cip='" + cip + '\'' +
                    ", cname='" + cname + '\'' +
                    ", cid='" + cid + '\'' +
                    '}';
        }
    }


}
