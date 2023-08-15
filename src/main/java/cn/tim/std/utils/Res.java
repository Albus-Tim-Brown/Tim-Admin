package cn.tim.std.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: albus
 * @Date: 2023/8/8 10:04
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.utils
 * @Description: 全局结果集
 */
@AllArgsConstructor
@Getter
@Setter
public class Res<T> {
    private Integer code;
    private String msg;
    private T data;

    //只提供消息
    public static <T> Res<T> ok(String msg){
        return new Res<T>(StatusCode.SUCCESS.getCode(), msg,null);
    }

    //只提供数据
    public static <T> Res<T> ok(T data){
        return new Res<T>(StatusCode.SUCCESS.getCode(),"",data);
    }

    //提供数据和消息
    public static <T> Res<T> ok(String msg, T data){
        return new Res<T>(StatusCode.SUCCESS.getCode(),msg,data);
    }

    //错误消息提示
    public static <T> Res<T> error(String msg){
        return new Res<T>(StatusCode.ERROR.getCode(), msg,null);
    }
}
