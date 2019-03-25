package com.github.cherrythefatbunny.demo.redis;

import com.github.cherrythefatbunny.demo.api.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(value = "infrastructure.enable",havingValue = "true")
public class RedisConfiguration {
    @Value("${embedded.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

    @Bean
    public RedisTemplate<String, String> stringTemplate(
            RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

        @Bean
    public RedisTemplate<String, Person> jsonTemplate(
            RedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Person> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Person.class);
        RedisTemplate<String,Person> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    public ReactiveRedisTemplate<String, Person> jsonReactiveTemplate(
            ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Person> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Person.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Person> builder =
                RedisSerializationContext.newSerializationContext();
        RedisSerializationContext<String, Person> context = builder
                .key(RedisSerializer.string())
                .value(valueSerializer)
                .hashKey(RedisSerializer.string())
                .hashValue(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisTemplate<String, String> stringReactiveTemplate(
            ReactiveRedisConnectionFactory factory) {
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
