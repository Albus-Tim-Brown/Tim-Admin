package cn.tim.std.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: tim-brown
 * @Date: 2023/8/15 15:00
 * @Project: Tim-Admin-Backend
 * @Package: cn.tim.std.config
 * @Description:
 */
@Configuration
public class RedisConfig {
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        //1.创建RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //2.设置连接工厂
        redisTemplate.setConnectionFactory(factory);
        //3.String序列号方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //4.使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer objectJackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<Object>(Object.class);
        //5.
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //6.设置key的序列化规则
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //设置hashkey的序列化规则
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //设置v的序列化规则
        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);
        //设置hashv的序列化规则
        redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();//初始化
        return redisTemplate;
    }
}
