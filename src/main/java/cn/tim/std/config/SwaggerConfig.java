package cn.tim.std.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author: albus
 * @Date: 2023/8/10 10:14
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.config
 * @Description:
 */
@Configuration
@EnableSwagger2WebMvc//开启swagger注解支持
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描包，如果配置错误swagger页面会出现No operations defined in spec!
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MES接口")
                .description("接口文档")
                .contact(new Contact("Tim_Brown","","albustimbrown@outlook.com"))
                .version("1.0")
                .build();
    }
}
