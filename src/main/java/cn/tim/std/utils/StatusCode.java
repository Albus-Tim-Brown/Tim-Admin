package cn.tim.std.utils;

/**
 * @Author: albus
 * @Date: 2023/8/8 10:11
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.utils
 * @Description: 状态码的枚举类型
 */
public enum StatusCode {
    SUCCESS(200),   //成功
    ERROR(500)     //失败
    ;

    private final Integer code;

    StatusCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
