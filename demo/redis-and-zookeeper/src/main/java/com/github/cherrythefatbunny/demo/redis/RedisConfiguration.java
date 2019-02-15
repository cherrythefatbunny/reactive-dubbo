package com.github.cherrythefatbunny.demo.redis;

import com.github.cherrythefatbunny.demo.api.Person;
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
    public RedisTemplate<String, Person> jsonTemplate(
            RedisConnectionFactory factory) {
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
}
