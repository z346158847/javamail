package com.zwj.javamail.config;

import com.zwj.javamail.entity.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailConfig {

    @Value("${mail.smtp.img}")
    private String imgPath;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

//    @Bean(name = "JavaMailSenderImpl")
//    public JavaMailSenderImpl getMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost(host);
//        javaMailSender.setUsername(account);
//        javaMailSender.setPassword(password);
//        javaMailSender.setPort(port);
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", isAuth);
//        properties.put("mail.smtp.timeout", outTime);
//        properties.put("mail.smtp.port", Integer.toString(port));
//        properties.put("mail.smtp.socketFactory.port", Integer.toString(port));
//        properties.put("mail.smtp.socketFactory.fallback", "false");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        javaMailSender.setJavaMailProperties(properties);
//        return javaMailSender;
//    }

    public void sendSimpleMail(EmailEntity email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(javaMailSender.getUsername());
        String receiver = email.getReceiver();
        String receivers[] = receiver.split(";");
        simpleMailMessage.setTo(receivers);
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getContent());
        javaMailSender.send(simpleMailMessage);
    }


    public void sendMimeMail(EmailEntity email, int index) {
        MimeMessage mimMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimMessage, true, "utf-8");
            messageHelper.setFrom(javaMailSender.getUsername());
            String receiver = email.getReceiver();
            String receivers[] = receiver.split(";");
            messageHelper.setTo(receivers);

            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getContent(), true);// Set the second
            // Param to True
            FileSystemResource res = new FileSystemResource(new File(imgPath + index +".jpg"));
            System.out.println(imgPath);
            messageHelper.addInline("imageurl", res);
            javaMailSender.send(mimMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
