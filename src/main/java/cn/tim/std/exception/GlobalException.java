package cn.tim.std.exception;

import cn.tim.std.utils.Res;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: albus
 * @Date: 2023/8/8 16:25
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.exception
 * @Description: 全局异常处理------核心:AOP 拦截
 */
@RestControllerAdvice
public class GlobalException {

    //Shiro异常处理
    @ExceptionHandler(ShiroException.class)
    public Res shiroException(ShiroException shiroException){
        if (shiroException instanceof UnknownAccountException){
            return Res.error("用户不存在!");
        }
        if (shiroException instanceof IncorrectCredentialsException){
            return Res.error("密码错误");
        }
        return Res.error("未知错误");
    }
//    @ExceptionHandler(UnknownAccountException.class)
//    public Res unknownAccountException(UnknownAccountException unknownAccountException){
//        return Res.error("用户不存在!");
//    }
//
//    @ExceptionHandler(IncorrectCredentialsException.class)
//    public Res incorrectCredentialsException(IncorrectCredentialsException incorrectCredentialsException){
//        return Res.error("密码错误");
//    }


}
