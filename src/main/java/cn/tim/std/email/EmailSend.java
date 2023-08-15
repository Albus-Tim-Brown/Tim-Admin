package cn.tim.std.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/10 17:12
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.email
 * @Description:
 */
@Component
public class EmailSend {

    @Resource//注入邮件服务类
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")//从application.yaml中获取
    private String myEmail;//发件人

    /*
    * toEmail 收件人
    * subject 标题
    * content 正文
    * */
    public boolean send(String toEmail, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(myEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        return true;
    }

}
