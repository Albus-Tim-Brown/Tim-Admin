package cn.tim.std;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: albus
 * @Date: 2023/8/8 09:24
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std
 * @Description:
 */
@SpringBootApplication
@MapperScan("cn.tim.std.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

