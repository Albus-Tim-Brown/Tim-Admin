package cn.tim.std.controller;

import cn.tim.std.email.EmailSend;
import cn.tim.std.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: albus
 * @Date: 2023/8/10 17:17
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.controller
 * @Description:
 */
@Api(tags = "邮件发送接口")
@RestController
@RequestMapping("email")
public class EmailController {

    @Resource
    private EmailSend emailSend;

    @ApiOperation("发送邮件")
    @PostMapping("sendEmail")
    public Res send(){
        String toEmail = "";
        String subject = "test email";
        String content = "......";
        emailSend.send(toEmail, subject, content);
        return Res.ok("邮件发送成功");
    }
}
