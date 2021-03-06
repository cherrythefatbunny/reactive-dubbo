package com.github.cherrythefatbunny.demo.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, String> stringTemplate(
            RedisConnectionFactory factory) {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    public ReactiveRedisTemplate<String, String> stringReactiveTemplate(
            ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<String> valueSerializer =
                new Jackson2JsonRedisSerializer<>(String.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder =
                RedisSerializationContext.newSerializationContext();
        RedisSerializationContext<String, String> context = builder
                .key(RedisSerializer.string())
                .value(RedisSerializer.string())
                .hashKey(RedisSerializer.string())
                .hashValue(RedisSerializer.string())
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
